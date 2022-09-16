(ns software.rooijakkers.full-stack.subs
  (:require
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.events :as events]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::deposit
 (fn [db]
   (get db :deposit 0)))

(re-frame/reg-sub
 ::products
 (fn [db]
   (:products db)))
