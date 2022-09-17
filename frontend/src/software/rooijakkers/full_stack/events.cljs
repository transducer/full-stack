(ns software.rooijakkers.full-stack.events
  (:require
   [ajax.core :as ajax]
   [day8.re-frame.http-fx]
   [re-frame.core :as re-frame]
   [software.rooijakkers.full-stack.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(def ^:private base-url "http://localhost:3000")

(re-frame/reg-fx
 ::alert
 (fn [{:keys [message]}]
   ;; TODO: nicer notifications
   (js/alert (str "ERROR: " message))))

(re-frame/reg-event-fx
 ::show-error
 (fn [_cofx [_ {:keys [status body]}]]
   {::alert {:message (str "status: " status (when body "body: ") body)}}))

(re-frame/reg-event-fx
 ::get-products
 (fn [_cofx _]
   {:http-xhrio {:method :get
                 :uri (str base-url "/product")
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::set-products]
                 :on-failure [::show-error]}}))

(re-frame/reg-event-db
 ::set-products
 (fn [db [_ response]]
   (assoc db :products (:data response))))

(re-frame/reg-event-fx
 ::deposit
 (fn [_cofx [_ amount]]
   {:http-xhrio {:method :post
                 :uri (str base-url "/deposit/" amount)
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::deposit-success]
                 :on-failure [::show-error]}}))

(re-frame/reg-event-fx
 ::get-deposit
 (fn [_cofx _]
   {:http-xhrio {:method :get
                 :uri (str base-url "/deposit")
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::deposit-success]
                 :on-failure [::show-error]}}))

(re-frame/reg-event-db
 ::deposit-success
 (fn [db [_ response]]
   (assoc db :deposit (or (:data response) 0))))

(re-frame/reg-event-fx
 ::buy
 (fn [_cofx _]
   {:http-xhrio {:method :post
                 :uri (str base-url "/buy")
                 ;; TODO: add body...
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::buy-success]
                 :on-failure [::show-error]}}))

(re-frame/reg-event-db
 ::buy-success
 (fn [db [_ response]]
   (assoc db :buy-result (:data response))))
