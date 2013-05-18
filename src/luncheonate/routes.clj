(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST)])
  (:require [compojure.route :as route]
            [compojure.core :as core]
            [ring.util.response :as resp]
            [luncheonate.models.user :as user]
            [luncheonate.views :as views]
            [clj-http.client :as client]))

;; Helpers
(defn sign-in-and-redirect [session user path]
  (assoc (resp/redirect path)
         :session
         (assoc session :user-id (:id user))))

(defn create-user [req]
  (if-let [user (user/find-by-email (:email (:params req)))]
    (if (user/authenticate user (:password (:params req)))
        (sign-in-and-redirect (:session req)
                              user
                              "/?signed-in=true")
        (resp/redirect "/?error=incorrect-password"))
    (sign-in-and-redirect (:session req)
                          (user/create (:params req))
                          "/?new-user=true")))

(def venues-url "https://api.foursquare.com/v2/venues/explore")
(def foursq-client-id "3ITISTF5LESZGZVSDBCWLSJEVNJUHK2NARZ4YKNO0E1ENMZU")
(def foursq-client-secret "NL0IV0C4FQYKDII4ZBXE3TFZFO5WVJLMLFAZWAFT1QTVNVAE")

(defn get-venue-information [ll]
  (let [res (client/get venues-url ; Query 4sq to get venue json
                {:accept :json
                 :as :json
                 :query-params {:ll ll
                                :limit 50
                                :v "20130504"
                                :section "food"
                                :client_id foursq-client-id
                                :client_secret foursq-client-secret}})]
    (map #(:venue %) ; get the relevant data from the JSON response
         (:items (first (:groups (:response (:body res))))))))

;; Routes
(defroutes routes
  (GET "/" req (views/new-users req))
  (POST "/users" req (create-user req))
  (GET "/venues" req (views/venues-list
                      {:venues (get-venue-information (req :ll))}))
  (route/resources "/")
  (route/not-found (views/four-oh-four)))
