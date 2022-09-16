(ns software.rooijakkers.full-stack.db
  (:require
   [software.rooijakkers.full-stack.change-calculator :refer [calculate-change]]))

(def ^:private last-id
  (atom 0))

;; map from product-id -> Product
(def ^:private product-db
  (atom {}))

;; map from user-id -> deposit
(def ^:private user-db
  (atom {}))

(defn- next-product-id! []
  (swap! last-id inc))

(defn add-product! [amount-available cost name seller-id]
  (swap! product-db
         assoc
         (next-product-id!)
         {:amount-available amount-available
          :cost cost
          :name name
          :seller-id seller-id}))

(defn- buy-product!
  "Buy `amount` of product with `product-id`. Returns map with name of product and
  amount ordered."
  [product-id amount]
  ;; Update amount in inventory
  (swap! product-db update-in [product-id :amount-available] - amount)
  {:amount amount
   :product (:name (@product-db product-id))})

(defn get-products! []
  (reduce (fn [m [id product]]
            (assoc m id product))
          {}
          @product-db))

(defn deposit!
  "Add `amount` to deposit of user with id `user-id` and return new amount"
  [user-id amount]
  (get (swap! user-db update user-id (fnil + 0) amount)
       user-id))

(defn get-deposit!
  "Get deposit for user with `user-id`. Returns `nil` when user is unknown."
  [user-id]
  (@user-db user-id))

(defn- pay!
  "Subtract `amount` from deposit of user with id `user-id` and return new amount"
  [user-id amount]
  (get (swap! user-db update user-id - amount)
       user-id))

(defn buy!
  "Buy product with `product-id` for user with `user-id`. Updates the inventory
  and user deposit if the buy is successful.
  Returns:
  `:not-enough-money` when the user does not have enough money
  `:not-enough-inventory` when there are not enough products in inventory
  a map with `{:spent x :change x :products {:name x :amount x}}` when successful"
  [user-id product-id amount]
  (let [deposit (@user-db user-id)
        product (@product-db product-id)
        total-price (* amount (get product :cost 0))]
    (cond (> amount (get product :amount-available 0)) :not-enough-inventory
          (> total-price deposit) :not-enough-money
          :else {:spent total-price
                 :change (calculate-change (pay! user-id total-price))
                 :products (buy-product! product-id amount)})))
