(ns software.rooijakkers.full-stack.views
  (:require
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.subs :as subs]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1.p-4
      "Hello from " @name]]))
