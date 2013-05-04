(ns luncheonate.core
    (:use [compojure.core :only (defroutes GET)]
          [ring.adapter.jetty :as ring]
          [hiccup.page :only (html5)])
    (:require [taoensso.carmine :as car]))

;; Templates
(defn index []
  (html5
    [:head
      [:title "Hello world!"]]
    [:body
      [:div#content "Hey there!"]]))

;; Routes
(defroutes routes
  (GET "/" [] (index)))


(defn -main []
  (run-jetty routes {:port 8080 :join? false}))


