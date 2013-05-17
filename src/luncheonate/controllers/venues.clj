(ns luncheonate.controllers.venues
  (:require [ring.util.response :as resp]
            [luncheonate.views.venues :as views]
            [clj-http.client :as client]))

(declare get-venue-information)

;; Actions
(defn index [params]
  (if (contains? params :ll)
      (views/index {:venues (get-venue-information (params :ll))})
      (resp/redirect "/404")))



;; Helpers
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
    (map #(% :venue) ; get the relevant data from the JSON response
         ((first (((res :body) :response) :groups)) :items))))

