(ns luncheonate.db.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema connectivity)
        (luncheonate.db helpers))
  (:require [luncheonate.db.config :as config]))

(alter-var-root #'lobos.migration/*migrations-namespace*
                (constantly 'luncheonate.db.migrations))

(defmigration add-users-table
  (up [] (create
           (tbl :users
                (varchar :email 50 :not-null)
                (varchar :password 50 :not-null))))
  (down [] (drop (table :users))))

(defmigration add-venues-table
  (up [] (create
           (tbl :venues
                (varchar :name 255 :not-null)
                (integer :rating)
                (varchar :foursquare_id 50)
                (float :latitude)
                (float :longitude))))
  (down [] (drop (table :venues))))

(defmigration add-saved-venues-table
  (up [] (create
           (tbl :saved_venues
                (integer :venue_id :not-null)
                (integer :user_id :not-null))))
  (down [] (drop (table :saved-venues))))

(defn -main []
  (open-global config/config)
  (print "Migrating...") (flush)
  (migrate)
  (println "Done."))

(defn rollback-migrations []
  (open-global config/config)
  (print "Rolling back...") (flush)
  (rollback)
  (println "Done."))
