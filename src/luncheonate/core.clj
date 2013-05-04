(ns luncheonate.core
    (:use [ring.adapter.jetty :as ring])
    (:use ring.middleware.reload)
    (:require [luncheonate.routes :as routes]))

(defn -main []
  (run-jetty (wrap-reload #'routes/routes '(luncheonate.core))
             {:port 8080 :join? false}))


