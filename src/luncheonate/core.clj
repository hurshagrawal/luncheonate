(ns luncheonate.core
  (:use [compojure.core :only (defroutes)]
        [ring.adapter.jetty :as ring])
  (:use ring.middleware.reload)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [luncheonate.routes :as routing]
            [luncheonate.views.layout :as layout]))


; Set up routes
(defroutes routes
  routing/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application
  (handler/site routes))

; Start application with reload on in development
(defn start-development []
  (run-jetty (wrap-reload #'application '(luncheonate.core))
             {:port 8080 :join? false}))

(defn start []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
       (run-jetty application {:port port :join? false})))

; Initialize application
(defn -main []
  (if (= (System/getenv "RING_ENV") "production")
      (start)
      (start-development)))
