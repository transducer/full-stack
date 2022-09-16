(ns software.rooijakkers.full-stack.service
  (:require
   #_[reitit.swagger :as swagger]
   #_[reitit.swagger-ui :as swagger-ui]
   [io.pedestal.http :as http]))

(def ping-handler
  (fn [_req] {:status 200, :body {:data "pong"}}))

(def routes
  `[[["/"
      ["/ping" {:get ping-handler}]]

     #_^{:no-doc true}
     ["/"
      ["/swagger.json" {:get (swagger/create-swagger-handler)}]
      ["/api-docs/*" {:get (swagger-ui/create-swagger-ui-handler)}]]]])

(def service
  (->
   {:env :prod
    ::http/routes routes
    ::http/allowed-origins ["http://localhost:8080"]
    ::http/resource-path "/public"
    ::http/type :jetty
    ::http/port 3000
    ::http/host "0.0.0.0"
    ::http/container-options {:h2c? true, :h2? false, :ssl? false}}
   http/default-interceptors
   (update ::http/interceptors into [http/json-body])))
