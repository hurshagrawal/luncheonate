(ns luncheonate.views.statics
  (:use [luncheonate.views.layout :only (application)]))

(defn index [params]
  (application "Sup homies"
    [:div.welcome "Welcome to luncheonate!"]
    [:button.js-find-venues "Find some places to eat!"]
    [:div#no-location-error
      [:h2 "Oops, I can't get your location"]]))
