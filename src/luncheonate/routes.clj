(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [compojure.route :as route]
            [luncheonate.views.layout :as layout]
            [luncheonate.controllers.statics :as statics]
            [luncheonate.controllers.venues :as venues]))

(defroutes routes
  (GET "/" [] (statics/index))
  (GET "/venues"
       {params :params}
       (venues/index params))
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))



