(ns luncheonate.routes
  (:use [compojure.core :only (defroutes GET POST ANY)])
  (:require [compojure.route :as route]
            [compojure.core :as core]
            [ring.util.response :as resp]
            [luncheonate.models :as models]
            [luncheonate.views :as views]
            [clj-http.client :as client]))

;; Helpers
(def venues-url "https://api.foursquare.com/v2/venues/explore")
(def foursq-client-id "3ITISTF5LESZGZVSDBCWLSJEVNJUHK2NARZ4YKNO0E1ENMZU")
(def foursq-client-secret "NL0IV0C4FQYKDII4ZBXE3TFZFO5WVJLMLFAZWAFT1QTVNVAE")

(defn sign-in-and-redirect [req user path]
  (assoc (resp/redirect path)
         :session
         (assoc (:session req)
                :user-id
                (:id user))))

(defn sign-out [req]
  (assoc (resp/redirect "/")
         :session
         (dissoc (:session req) :user-id)))

(defn get-venue-information [ll]
  "Query foursquare to get venue JSON"
  (let [res (client/get venues-url
                {:accept :json
                 :as :json
                 :query-params {:ll ll
                                :limit 50
                                :v "20130504"
                                :section "food"
                                :client_id foursq-client-id
                                :client_secret foursq-client-secret}})]
    (map #(:venue %) ; get the relevant data from the JSON response
         (-> res :body :response :groups first :items))))

(defn create-user [req]
  (if-let [user (models/find-user
                  (select-keys (:params req) [:email]))]
    (if (models/authenticate-user user (-> req :params :password))
        (sign-in-and-redirect req user "/?signed-in=true")
        (resp/redirect (str "/?error=incorrect-password&email="
                            (-> req :params :email))))
    (sign-in-and-redirect req (models/create-user (:params req)) "/")))

(defn home [req]
  (let [user (models/find-user {:id (-> req :session :user-id)} [models/venue])]
    (views/home {:user user})))

(defn venues-nearby [req]
  (if (-> req :params :ll)
      (views/home {:venues (get-venue-information (:ll req))})
      (resp/redirect "/404")))

;; Routes
(defroutes signed-in-routes
  (GET "/" req (home req))
  (GET "/signout" req (sign-out req)))

(defroutes signed-out-routes
  (GET "/" req (views/new-users req))
  (POST "/users" req (create-user req)))

(defroutes routes
  (ANY "*" req (if (-> req :session :user-id)
                   signed-in-routes
                   signed-out-routes))
  (route/resources "/")
  (route/not-found (views/four-oh-four)))

