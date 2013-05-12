(ns luncheonate.db.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema connectivity)
        (luncheonate.db helpers))
  (:require [luncheonate.db.config :as config]))

;(alter-var-root #'lobos.migration/*migrations-namespace*
                ;(constantly 'luncheonate.db.migrations))

;(defmigration add-users-table
  ;(up [] (create
            ;(tbl :users
              ;(varchar :email 50 :not-null)
              ;(varchar :password 50 :not-null))))
  ;(down [] (drop (table :comments))))


;(defn -main []
  ;(open-global config/config)
  ;(print "Migrating...") (flush)
  ;(migrate)
  ;(println "Done."))
