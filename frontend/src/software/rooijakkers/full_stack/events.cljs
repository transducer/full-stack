(ns software.rooijakkers.full-stack.events
  (:require
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
