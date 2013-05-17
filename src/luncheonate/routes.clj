(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [compojure.route :as route]
            [luncheonate.views.layout :as layout]
            [luncheonate.controllers.users :as users]
            [luncheonate.controllers.venues :as venues]))

(defroutes routes
  (GET "/" req (users/new req))
  (POST "/users" req (users/create req))
  (GET "/venues" req (venues/index))
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))


