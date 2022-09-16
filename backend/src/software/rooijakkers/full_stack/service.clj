(ns software.rooijakkers.full-stack.service
  (:require
   ;; [reitit.swagger :as swagger]
   ;; [reitit.swagger-ui :as swagger-ui]
   [io.pedestal.http :as http]
   [io.pedestal.http.body-params :refer [body-params]]
   [software.rooijakkers.full-stack.db :as db]))

(defn get-product [_req]
  {:status 200
   :body {:data (db/get-products!)}})

(defn post-product [{product :json-params :as _req}]
  {:status 200
   ;; TODO: seller-id
   :body {:data (db/add-product! product :seller-id)}})

(defn delete-product [{{:keys [id]} :path-params :as _req}]
  {:status 200
   :body {:data (db/delete-product! (parse-long id))}})

(defn post-deposit [{{:keys [amount]} :path-params :as _req}]
  {:status 200
   ;; TODO: user-id
   :body {:data (db/deposit! :user-id (parse-long amount))}})

(defn delete-deposit [_req]
  {:status 200
   ;; TODO: user-id
   :body {:data (db/delete-deposit! :user-id)}})

(defn post-buy [{{:keys [product-id amount]} :json-params
            :as _req}]
  {:status 200
   ;; TODO: user-id
   :body {:data (db/buy! :user-id product-id (parse-long amount))}})

(defn get-user [_req]
  ;; TODO
  {:status 200
   :body {:data {}}})

(def routes
  `[[["/"
      ["/product" {:get get-product
                   :post post-product}]
      ["/product/:id" {:delete delete-product}]
      ["/deposit" {:delete delete-deposit}]
      ["/deposit/:amount" {:post post-deposit}]
      ["/buy" {:post post-buy}]
      ["/user" {:get get-user}]]

     ;; TODO: Swagger
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
   (update ::http/interceptors into [http/json-body (body-params)])))
