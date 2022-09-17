(ns software.rooijakkers.full-stack.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.events :as events]
   [software.rooijakkers.full-stack.views :as views]
   [software.rooijakkers.full-stack.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (re-frame/dispatch-sync [::events/get-products])
  (re-frame/dispatch-sync [::events/deposit])
  (dev-setup)
  (mount-root))
