(ns software.rooijakkers.full-stack.views
  (:require
   [re-frame.core :as re-frame]
   [reagent.core :as r]
   [software.rooijakkers.full-stack.events :as events]
   [software.rooijakkers.full-stack.subs :as subs]))

(defn- container [& body]
  `[:div.antialiased.text-gray-900.px-6.font-hand-writing
    [:div.max-w-xl.mx-auto.py-12.md:max-w-4xl.text-lg
     ~@body]])

(defn- input [placeholder state & [opts]]
  [:label.block
   [:input.block.w-full.bg-gray-500.striped.focus:border-gray-500.focus:ring-0.placeholder-black.p-1
    (merge
     {:placeholder placeholder
      :type "text"
      :on-change (fn [e] (reset! state (-> e .-target .-value)))}
     opts)]])

(defn- label [text]
  [:label.block.flex.items-center.justify-center
   [:span text]])

(defn- button [text on-click]
  [:button.bg-gray-500.striped.py-2.px-8.border.border-gray-500.focus:outline-none.text-xl.w-full
   {:on-click on-click}
   text])

(defn- header [text]
  [:h2.col-span-full.text-3xl.font-bold.text-center
   text])

(defn- grid [& body]
  (into
   [:div.grid.grid-cols-3.gap-6.border-2.border-black.p-6.bg-gray-100.striped]
   body))

(defn- products-window [products]
  [:div.col-span-full
   (if (seq products)
     (doall
      (for [[product-id {:keys [name amount-available cost]}] products]
        ^{:key product-id}
        [:div amount-available \space name \space "for" \space cost]))
     [:div "No products available 😔"])])

(defn- deposited []
  (let [deposit (re-frame/subscribe [::subs/deposit])]
    [:div.col-span-full "Money in vending machine: " @deposit]))

(defn- vending-machine []
  (re-frame/dispatch [::events/get-products])
  (let [deposit (r/atom 0)
        amount (r/atom 0)
        product-id (r/atom "")
        products (re-frame/subscribe [::subs/products])]
    (fn []
      [:div.py-12
       [:div.mt-8.max-w-md
        [grid
         [header "🧨 Vending machine"]
         [deposited]
         [products-window @products]
         [label "product id"]
         [:div.col-span-2
          [input product-id "1" {:auto-focus true}]]
         [label "amount"]
         [:div.col-span-2
          [input amount " "]]
         [:div.col-start-3
          [button "Buy" (fn [] (re-frame/dispatch [::events/buy nil]))]]
         [label "amount"]
         [:div.col-span-2
          [input deposit " "]]
         [:div.col-start-3
          [button "Deposit" (fn [] (re-frame/dispatch [::events/deposit nil]))]]]]])))

(defn main-panel []
  [container
   [vending-machine]])
