(ns luncheonate.controllers.users
  (:require [ring.util.response :as resp]
            [luncheonate.models.user :as user]
            [luncheonate.views.users :as views]))

(declare sign-in sign-in-and-redirect)

;; Actions
(defn new [req]
  (views/new req))

(defn create [req]
  (if-let [user (user/find-by-email (:email (:params req)))]
    (if (user/authenticate user (:password (:params req)))
        (sign-in-and-redirect (:session req)
                              user
                              "/home?signed-in=true")
        (resp/redirect "/?error=incorrect-password"))
    (sign-in-and-redirect (:session req)
                          (user/create (:params req))
                          "/home?new-user=true")))

(defn home []
  ())

;; Helpers
(defn sign-in [session user]
  (write-session session "user_id" (:id user))

(defn sign-in-and-redirect [session user path]
  (sign-in session user)
  (resp/redirect path))

