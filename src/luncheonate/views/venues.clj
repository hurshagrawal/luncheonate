(ns luncheonate.views.venues
  (:use [luncheonate.views.layout :only (application)]))

(defn index [params]
  (application "Venues nearby"
    [:ul.venues
      (for [venue (params :venues)]
        [:li.venue
          [:div.name
            (venue :name)]])]))

