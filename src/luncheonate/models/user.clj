(ns luncheonate.models.user
  (:use luncheonate.config.db)
  (:require [taoensso.carmine :as car]))

; Private

(def users-key "users")
(def email-lookup-key "users::lookup::email")

(def alphanumeric "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890")
(defn get-random-id []
  (apply str (repeatedly 10 #(rand-nth alphanumeric))))

; Public

(defn find-by-email [email]
  (db (car/hget users-key
               (db (car/hget email-lookup-key email)))))

(defn authenticate [user password]
  (= (user :password) password))

(defn create [attributes]
  (let [user-id (get-random-id)]
    (db (car/hset users-key user-id attributes))
    (db (car/hset email-lookup-key (attributes :email) user-id))))
