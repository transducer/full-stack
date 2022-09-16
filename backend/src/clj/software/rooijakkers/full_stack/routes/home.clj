(ns software.rooijakkers.full-stack.routes.home
  (:require
   [clojure.java.io :as io]
   [ring.util.http-response :as response]
   [ring.util.response]
   [software.rooijakkers.full-stack.layout :as layout]
   [software.rooijakkers.full-stack.middleware :as middleware]))

(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [ ""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]])
