(ns software.rooijakkers.full-stack.server
  (:gen-class) ; for -main method in uberjar
  (:require
   [io.pedestal.http :as server]
   [software.rooijakkers.full-stack.service :as service]))

(defonce runnable-service (server/create-server service/service))

(defn -main
  "The entry-point for 'lein run'"
  [& _args]
  (println "\nCreating your server...")
  (server/start runnable-service))
