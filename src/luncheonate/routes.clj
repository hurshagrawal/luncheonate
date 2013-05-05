(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [compojure.route :as route]
            [luncheonate.views.layout :as layout]
            [luncheonate.controllers.users :as users]
            [luncheonate.controllers.venues :as venues]))

(defroutes routes
  (GET "/" [] (users/new))
  (POST "/users"
        {session :session params :params}
        (users/create session params))
  (GET "/venues"
       {params :params}
       (venues/index params))
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))



