(ns luncheonate.views.users
  (:use [luncheonate.views.layout :only (application)]))

(defn new []
  (application "Sup homies"
    [:div.welcome "Welcome to luncheonate!"]
    [:button.js-find-venues "Find some places to eat!"]
    [:div#no-location-error
      [:h2 "Oops, I can't get your location"]]))
