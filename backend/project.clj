(defproject software.rooijakkers.full-stack "0.0.1-SNAPSHOT"
  :description "Vending machine backend API"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [com.fasterxml.jackson.core/jackson-core "2.13.4"]
                 [cheshire "5.11.0"]
                 [metosin/reitit-ring "0.5.18"]
                 [metosin/reitit-swagger "0.5.13"]
                 [metosin/reitit-swagger-ui "0.5.13"]
                 [ch.qos.logback/logback-classic "1.2.9" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]
                 [org.slf4j/log4j-over-slf4j "1.7.30"]
                 [org.clojure/core.logic "1.0.0"]]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources"]
  :profiles {:dev {:aliases {"run-dev" ["trampoline" "run" "-m" "software.rooijakkers.full-stack.server/run-dev"]}
                   :dependencies [[io.pedestal/pedestal.service-tools "0.5.10"]]}
             :uberjar {:aot [software.rooijakkers.full-stack.server]}}
  :main ^{:skip-aot true} software.rooijakkers.full-stack.server)
