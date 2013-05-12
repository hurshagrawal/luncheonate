(ns luncheonate.controllers.users
  (:use luncheonate.config.db)
  (:require [ring.util.response :as resp]
            [luncheonate.models.user :as user]
            [luncheonate.views.users :as views]))

; Helper functions
(defn sign-in [session user] ())
(defn sign-in-and-redirect [session user path]
  (sign-in session user)
  (resp/redirect path))

; Actions

(defn new [session]
  (views/new))

(defn create [session params]
  (if-let [user (user/find-by-email (:email params))]
    (if (user/authenticate user (:password params))
        (sign-in-and-redirect session user "/home?signed-in=true")
        (resp/redirect "/?error=incorrect-password"))
    (sign-in-and-redirect session (user/create params) "/home?new-user=true")))

(defn home []
  ())

