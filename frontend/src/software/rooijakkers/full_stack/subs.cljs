(ns software.rooijakkers.full-stack.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::deposit
 (fn [db]
   (get db :deposit 0)))

(re-frame/reg-sub
 ::products
 (fn [db]
   (:products db)))

(re-frame/reg-sub
 ::deposit-result
 (fn [db]
   (:deposit db)))

(re-frame/reg-sub
 ::buy-result
 (fn [db]
   (:buy-result db)))
