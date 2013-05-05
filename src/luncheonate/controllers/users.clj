(ns luncheonate.controllers.users
  (:use luncheonate.config.db)
  (:require [taoensso.carmine :as car]
            [luncheonate.views.users :as views]))

(defn new []
  (views/new))

(defn create []
  (views/new))

