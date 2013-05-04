(ns luncheonate.views.layout
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css)]))

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
    [:div#content.container body]]))

(defn four-oh-four []
  (application "Page Not Found"
               [:div#four-oh-four "The page you requested could not be found"]))
