(ns software.rooijakkers.full-stack.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [software.rooijakkers.full-stack.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[software.rooijakkers.full-stack started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[software.rooijakkers.full-stack has shut down successfully]=-"))
   :middleware wrap-dev})
