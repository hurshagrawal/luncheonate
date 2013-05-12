(ns luncheonate.models.user
  (:use luncheonate.config.db)
  (:use korma.core))

(defentity user
  (table :users)
  (entity-fields :id :name))

(def authenticate [user password]
  (= (:password user) password))

(def find-by-email [email]
  (select user
    (where {:email email})))

(def create [params]
  (insert user
    (values {:email (:email params)
             :password (:password params)})))
