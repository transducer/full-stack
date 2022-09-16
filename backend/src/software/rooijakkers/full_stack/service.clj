(ns software.rooijakkers.full-stack.service
  (:require
   [io.pedestal.http :as http]
   [io.pedestal.http.body-params :as body-params]
   [io.pedestal.http.route :as route]
   [ring.util.response :as ring-resp]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

;; Defines "/" and "/about" routes with their associated :get handlers.
;; The interceptors defined after the verb map (e.g., {:get home-page}
;; apply to / and its children (/about).
(def common-interceptors [(body-params/body-params) http/html-body])

(def routes
  `[[["/" {:get home-page}
      ^:interceptors [(body-params/body-params) http/html-body]
      ["/about" {:get about-page}]]]])

(def service
  {:env :prod
   ::http/routes routes
   ::http/allowed-origins ["http://localhost:8080"]

   ::http/resource-path "/public"
   ::http/type :jetty
   ::http/port 3000
   ::http/host "0.0.0.0"
   ::http/container-options {:h2c? true
                             :h2? false
                             :ssl? false}})
