(ns luncheonate.views.users
  (:use [luncheonate.views.layout :only (application)]
        [hiccup.form]))

(defn new []
  (application "Sup homies"
    [:div.welcome
      [:h3 "Welcome to luncheonate!"]
      [:h4 "Sign in or sign up to continue"]]
    [:div.sign-up
      (form-to [:post "/users"]
        (text-field {:placeholder "joe@gmail.com"} :email)
        (password-field {:placeholder "**********"} :password)
        (submit-button "Sign up or sign in"))]))

    ;[:button.js-find-venues "Find some places to eat!"]
    ;[:div#no-location-error
      ;[:h2 "Oops, I can't get your location"]]


