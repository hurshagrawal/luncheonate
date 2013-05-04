(ns luncheonate.views.layout
  (:use [hiccup.page :only (html5)]))

(defn four-oh-four []
  (html5
    [:head
      [:title "404"]]
    [:body
      [:div#content "404"]]))
