(ns luncheonate.views.statics
  (:use [hiccup.page :only (html5)]))

(defn index [params]
  (html5
    [:head
      [:title "Sup homies"]]
    [:body
      [:div#content "sup " (params :foo)]]))
