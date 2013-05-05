(ns luncheonate.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css include-js)]))

(defn application [title & body]
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
    [:div#content.container body]
    [:div.js
      (include-js "//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js")
      (include-js "/javascripts/index.js")]]))

(defn four-oh-four []
  (application "Page Not Found"
               [:div#four-oh-four "The page you requested could not be found"]))
