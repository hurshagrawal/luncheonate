(ns luncheonate.views.statics
  (:use [hiccup.page :only (html5)]))

(defn index [params]
  (html5
    [:head
      [:title "Sup homies"]]
    [:body
      [:div#content "Yo, this is real"]]))
