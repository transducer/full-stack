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
