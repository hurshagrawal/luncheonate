(ns luncheonate.controllers.venues
  (:use luncheonate.config.db)
  (:require [taoensso.carmine :as car]
            [luncheonate.views.venues :as views]))

(defn index [params]
  (views/index params))


