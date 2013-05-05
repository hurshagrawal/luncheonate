(ns luncheonate.controllers.statics
  (:use luncheonate.config.db)
  (:require [taoensso.carmine :as car]
            [luncheonate.views.statics :as views]))

(defn index []
  (views/index {}))


