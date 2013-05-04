(ns luncheonate.views.statics
  (:use [luncheonate.views.layout :only (application)]
        [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css)]))

(defn index [params]
  (application "Sup homies"
    [:div.welcome "Welcome to luncheonate!"]))
