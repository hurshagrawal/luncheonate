(ns luncheonate.core
  (:use [compojure.core :only (defroutes)]
        [ring.adapter.jetty :as ring])
  (:use ring.middleware.reload)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [luncheonate.routes :as routing]
            [luncheonate.views.layout :as layout]))


(defroutes routes
  routing/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (run-jetty (wrap-reload #'application '(luncheonate.core))
             {:port port :join? false}))

(defn -main []
  (let [port (Integer/parseInt
               (or (System/getenv "PORT") "8080"))]
  (start port)))

