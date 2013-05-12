(defproject luncheonate "0.1.0-SNAPSHOT"
  :description "Find lunch!"
  :url "https://github.com/hurshagrawal/luncheonate"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [korma "0.3.0-RC5"]
                 [postgresql "9.1-901.jdbc4"]
                 [lobos "1.0.0-beta1"]
                 [ring/ring-jetty-adapter "1.1.8"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [ring/ring-devel "1.1.8"]
                 [clj-http "0.7.2"]]
  :main luncheonate.core)
