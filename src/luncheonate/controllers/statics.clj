(ns luncheonate.controllers.statics
  (:use luncheonate.config.db)
  (:require [taoensso.carmine :as car]
            [luncheonate.views.statics :as views]))


(defn index []
  (db (car/set "foo" "barring"))
  (views/index {:foo (db (car/get "foo"))}))


