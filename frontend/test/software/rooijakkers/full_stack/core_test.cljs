(ns software.rooijakkers.full-stack.core-test
  (:require
   [cljs.test :refer-macros [deftest is]]
   [day8.re-frame.test :as re-frame.test]
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.events :as events]
   [software.rooijakkers.full-stack.subs :as subs]))

(deftest init-test
  (re-frame.test/run-test-sync
   (re-frame/dispatch [::events/initialize-db])
   (let [deposit (re-frame/subscribe [::subs/deposit])]
     (is (zero? @deposit)))))
