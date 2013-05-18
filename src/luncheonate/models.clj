(ns luncheonate.models
  (:use [korma.core])
  (:require [luncheonate.db.config :as config]))

(declare user venue saved-venue)

;; User
(defentity user
  (table :users)
  (database config/db)
  (entity-fields :id :name)
  (has-many saved-venue)
  (many-to-many venue :saved-venue))

(defn authenticate-user [user password]
  (= (:password user) password))

(defn find-user [params]
  (first (select user (where params))))

(defn create-user [params]
  (insert user
    (values {:email (:email params)
             :password (:password params)})))

;; Venue
(defentity venue
  (table :venues)
  (database config/db)
  (entity-fields :id
                 :name
                 :rating
                 :foursquare_id
                 :latitude
                 :longitude)
  (has-many saved-venue)
  (many-to-many user :saved-venue))

;; Saved Venue
(defentity saved-venue
  (table :saved-venues)
  (database config/db)
  (entity-fields :id :user_id :venue_id)
  (belongs-to user)
  (belongs-to venue))


