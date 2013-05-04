(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [luncheonate.controllers.statics :as statics]))

(defroutes routes
  (GET "/" [] (statics/index)))


