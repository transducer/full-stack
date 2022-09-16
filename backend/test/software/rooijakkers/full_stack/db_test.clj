(ns software.rooijakkers.full-stack.db-test
  (:require
   [clojure.test :refer [testing deftest is]]
   [software.rooijakkers.full-stack.db :as sut]))

(deftest ^:integration test-flow
  (let [amount-available 1
        cost 100
        product-name "product"
        seller-id "seller-id"
        user-id "user-id"
        expected-product-id 1]
    (testing "add product"
      (sut/add-product! amount-available cost product-name seller-id)
      (is (= {expected-product-id {:amount-available amount-available
                                   :cost cost
                                   :name product-name
                                   :seller-id seller-id}}
             (sut/get-products!))))
    (testing "depositing"
      (sut/deposit! user-id 100)
      (sut/deposit! user-id 5)
      (is (= 105 (sut/get-deposit! user-id)))
      (is (nil? (sut/get-deposit! :unknown-user-id))))
    (testing "buying"
      (let [amount 1
            result (sut/buy! user-id expected-product-id amount)]
        (is (= {1 {:amount-available 0
                   :cost 100
                   :name "product"
                   :seller-id "seller-id"}}
               (sut/get-products!)))
        (is (= {:spent 100
                :change {:n5 1 :n10 0 :n20 0 :n50 0 :n100 0}
                :products {:amount 1 :product product-name}}
               result))))
    (testing "some edge cases"
      (testing "non existing product"
        (is (= :not-enough-inventory (sut/buy! user-id :non-existing-id 1))))
      (testing "not enough deposit since we already sold"
        (is (= :not-enough-inventory (sut/buy! user-id expected-product-id 1))))
      (testing "not enough money since user already spent his deposit"
        (sut/add-product! amount-available cost product-name seller-id)
        (is (= :not-enough-money (sut/buy! user-id (inc expected-product-id) 1)))))))
