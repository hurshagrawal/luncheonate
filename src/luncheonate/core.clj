(ns luncheonate.core
  (:use [compojure.core :only (defroutes)]
        [ring.adapter.jetty :as ring])
  (:use ring.middleware.reload)
  (:require [ring.middleware.session :as session]
            [ring.middleware.session.cookie :as cookie]
            [compojure.handler :as handler]
            [luncheonate.routes :as routes]))


; Options
(def cookie-key "c0079f5a9ef363e7c330af9d59888e0c1064363cae63b7e")

(def app-options
  {:session {:store (cookie/cookie-store {:key cookie-key})
             :cookie-name "luncheonate-session"}})

(def app
  (handler/site routes/routes app-options))

; Start application with reload on in development
(defn start-development []
  (run-jetty (wrap-reload #'app '(luncheonate.core))
             {:port 8080 :join? false}))

(defn start []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
       (run-jetty app {:port port :join? false})))

; Initialize application
(defn -main []
  (if (= (System/getenv "RING_ENV") "production")
      (start)
      (start-development)))
