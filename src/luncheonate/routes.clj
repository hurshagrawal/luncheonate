(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [luncheonate.controllers.statics :as statics]
            [luncheonate.controllers.venues :as venues]))

(defroutes routes
  (GET "/" [] (statics/index))
  (GET "/venues" [& params] (venues/index params)))



