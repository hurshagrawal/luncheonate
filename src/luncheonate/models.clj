(ns luncheonate.models
  (:use [korma.core])
  (:require [luncheonate.db.config :as config]))

(declare user venue saved-venue)

;; User
(defentity user
  (table :users)
  (database config/db)
  (entity-fields :id :name)
  (has-many saved-venue {:fk :user_id})
  (many-to-many venue :saved_venues {:lfk :venue_id
                                     :rfk :user_id}))

(defn authenticate-user [user password]
  (= (:password user) password))

(defmacro find-user [params & [includes]]
  "Returns first user matching 'params' with included associations"
  (list `first (concat `(select user (where ~params))
                       (map (fn [i] (list `with i))
                            (if (nil? includes) [] includes)))))

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
  (has-many saved-venue {:fk :venue_id})
  (many-to-many user :saved_venues {:lfk :venue_id
                                     :rfk :user_id}))

(defn get-venues-for-user [user]
  (select venue
          (join saved-venue (= :saved_venues.venue_id :id))
          (where {:saved_venues.user_id (:id user)})))

;; Saved Venue
(defentity saved-venue
  (table :saved_venues)
  (database config/db)
  (entity-fields :id :user_id :venue_id)
  (belongs-to user {:fk :user_id})
  (belongs-to venue {:fk :venue_id}))


