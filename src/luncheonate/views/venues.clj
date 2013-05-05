(ns luncheonate.views.venues
  (:use [luncheonate.views.layout :only (application)]))

(defn index [params]
  (application "Your venues"
    [:div.venues "" params]))
