(ns software.rooijakkers.full-stack.change-calculator
  (:require
   [clojure.core.logic :refer [run*] :as logic]
   [clojure.core.logic.fd :as fd]))

(def ^:private coin-amount (long 1e6))

(defn calculate-change
  "Calculates change for `amount` in types of coins, preferring larger coins when
  available. Returns a map of type of coins (`:n5`, `:n10`, `:n20`, `:n50`,
  `n100`) and their amount.

  NOTE: assumption is now a million coins in the machine for every type, better would
  be to keep track of amount of coins."
  [amount]
  (some->>
   (run* [n5 n10 n20 n50 n100]
     (fd/in n5 (fd/interval 0 coin-amount))
     (fd/in n10 (fd/interval 0 coin-amount))
     (fd/in n20 (fd/interval 0 coin-amount))
     (fd/in n50 (fd/interval 0 coin-amount))
     (fd/in n100 (fd/interval 0 coin-amount))
     (fd/eq (= (+ (* n5 5) (* n10 10) (* n20 20) (* n50 50) (* n100 100))
               amount)))
   first
   (zipmap [:n5 :n10 :n20 :n50 :n100])))
