(ns luncheonate.controllers.statics
  (:require [luncheonate.views.statics :as views]
            [luncheonate.config.db :only (db)]))

(defn index []

  (views/index {}))


