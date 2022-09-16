(ns software.rooijakkers.full-stack.service
  (:require
   [io.pedestal.http :as http]
   [io.pedestal.http.route :as route]
   [io.pedestal.http.body-params :as body-params]
   [ring.util.response :as ring-resp]))

(defn about-page
  [request]
  (ring-resp/response (format "Clojure %s - served from %s"
                              (clojure-version)
                              (route/url-for ::about-page))))

(defn home-page
  [request]
  (ring-resp/response "Hello World!"))

(def common-interceptors
  [(body-params/body-params) http/html-body])

(def routes
  `[[["/" {:get home-page}
      ^:interceptors [(body-params/body-params) http/html-body]
      ["/about" {:get about-page}]]]])

;; Consumed by software.rooijakkers.full-stack.server/create-server
;; See http/default-interceptors for additional options you can configure
(def service
  {:env :prod
   ;; You can bring your own non-default interceptors. Make
   ;; sure you include routing and set it up right for
   ;; dev-mode. If you do, many other keys for configuring
   ;; default interceptors will be ignored.
   ;; ::http/interceptors []
   ::http/routes routes

   ;; Uncomment next line to enable CORS support, add
   ;; string(s) specifying scheme, host and port for
   ;; allowed source(s):
   ;;
   ;; "http://localhost:8080"
   ;;
   ::http/allowed-origins ["scheme://localhost:8080"]

   ;; Tune the Secure Headers
   ;; and specifically the Content Security Policy appropriate to your service/application
   ;; For more information, see: https://content-security-policy.com/
   ;;   See also: https://github.com/pedestal/pedestal/issues/499

   ;; Root for resource interceptor that is available by default.
   ::http/resource-path "/public"

   ;; Either :jetty, :immutant or :tomcat (see comments in project.clj)
   ;;  This can also be your own chain provider/server-fn --
   ;;  http://pedestal.io/reference/architecture-overview#_chain_provider
   ::http/type :jetty
   ;;::http/host "localhost"
   ::http/port 3000
   ;; Options to pass to the container (Jetty)
   ::http/container-options {:h2c? true
                             :h2? false
                             :ssl? false}})
