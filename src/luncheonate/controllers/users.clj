(ns luncheonate.controllers.users
  (:use luncheonate.config.db)
  (:require [ring.util.response :as resp]
            [luncheonate.models.user :as user]
            [luncheonate.views.users :as views]))

; Helper functions

(defn sign-in [session user] ())

; Actions

(defn new [session]
  (views/new))

    (defn create [session params]
      (let [user (user/find-by-email (params :email))]
        (if user
            (if (user/authenticate user (params :password))
                (do (sign-in session user)
                    (resp/redirect "/home?signed-in=true"))
                (resp/redirect "/?error=incorrect-password"))
            (let [new-user (user/create params)]
              (sign-in session new-user)
              (resp/redirect "/home?new-user=true")))))

(defn home []
  ())

