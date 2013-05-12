(ns luncheonate.models.user
  (:use korma.core))

(defentity user
  (table :users)
  (entity-fields :id :name))

(defn authenticate [user password]
  (= (:password user) password))

(defn find-by-email [email]
  (select user
    (where {:email email})))

(defn create [params]
  (insert user
    (values {:email (:email params)
             :password (:password params)})))
