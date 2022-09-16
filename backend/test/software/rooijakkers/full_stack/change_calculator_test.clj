(ns software.rooijakkers.full-stack.change-calculator-test
  (:require
   [clojure.test :refer [deftest are]]
   [software.rooijakkers.full-stack.change-calculator :as sut]))

(deftest change-calculator-test
  (are [expected amount] (= expected (sut/calculate-change amount))
    {:n100 0 :n50 0 :n20 0 :n10 1 :n5 0} 10
    {:n100 0 :n50 0 :n20 1 :n10 0 :n5 1} 25
    {:n100 1 :n50 0 :n20 0 :n10 0 :n5 0} 100
    {:n100 1 :n50 0 :n20 0 :n10 0 :n5 1} 105
    {:n100 1 :n50 0 :n20 1 :n10 0 :n5 1} 125
    {:n100 1 :n50 1 :n20 0 :n10 0 :n5 1} 155
    {:n100 0 :n50 0 :n20 0 :n10 0 :n5 0} 0
    {:n100 0 :n50 0 :n20 0 :n10 0 :n5 0} nil
    {:n100 19863 :n50 1 :n20 1 :n10 0 :n5 1} 1986375
    nil (long 1e12))) ; no solution because not enough coins
