(ns luncheonate.views
  (:use [hiccup.form]
        [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css include-js)]))

(defn layout [params title & body]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
      [:title title]
      (include-css "/stylesheets/base.css")]
  [:body
    [:div#header
      [:h1.container "Luncheonate!"]]
      (if-let [user (:user params)]
        [:div.user-card
         "You're logged in as"
         [:h2 (:email user)]])
    [:div#content.container body]
    [:div.js
      (include-js "//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js")
      (include-js "/javascripts/index.js")]]))

(defn new-users [params]
  (layout params "Sup homies"
    [:div.welcome
      [:h3 "Welcome to luncheonate!"]
      [:h4 "Sign in or sign up to continue"]]
    [:div.sign-up
      (form-to [:post "/users"]
        (text-field {:placeholder "joe@gmail.com"} :email)
        (password-field {:placeholder "**********"} :password)
        (submit-button "Sign up or sign in"))]
    [:div nil params]))

(defn home [params]
  (layout params "Venues nearby"
    [:ul.venues
      (for [venue (-> params :user :venues)]
        [:li.venue
          [:div.name
            (venue :name)]])]))

(defn four-oh-four []
  (layout {} "Page Not Found"
               [:div#four-oh-four "The page you requested could not be found"]))

