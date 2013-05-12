(ns luncheonate.models.user
  (:use korma.core)
  (:require [luncheonate.db.config :as config]))

(defentity user
  (table :users)
  (database config/db)
  (entity-fields :id :name))

(defn authenticate [user password]
  (= (:password user) password))

(defn find-by-email [email]
  (first (select user
           (where {:email email}))))

(defn create [params]
  (insert user
    (values {:email (:email params)
             :password (:password params)})))
