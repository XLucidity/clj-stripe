(ns clj-stripe.invoiceitems
    "Functions for Stripe Invoice Items API"
    (:use clj-stripe.common)
    (:require [clj-stripe.util :as util]))

(defn create-invoice-item
  [customer money-amount & extra-info]
  (apply util/merge-maps {:operation :create-invoice-item} customer money-amount extra-info)
  )
(defmethod execute :create-invoice-item [op-data] (util/post-request stripe-token (str *api-root* "/invoiceitems") (dissoc op-data :operation)))

(defn get-invoice-item
  [id]
  {:operation :get-invoice-item "id" id}
  )
(defmethod execute :get-invoice-item [op-data] (util/get-request stripe-token (str *api-root* "/invoiceitems/" (get op-data "id"))))

(defn update-invoice-item
  [id money-amount & extra-info]
  (apply util/merge-maps {:operation :update-invoice-item :id id} money-amount extra-info)
  )
(defmethod execute :update-invoice-item [op-data] (util/post-request stripe-token (str *api-root* "/invoiceitems/" (get op-data :id)) (dissoc op-data :operation :id)))

(defn delete-invoice-item
  [id]
  {:operation :delete-invoice-item :id id}
  )
(defmethod execute :delete-invoice-item [op-data] (util/delete-request stripe-token (str *api-root* "/invoiceitems/" (get op-data :id))))

(defn get-all-invoice-items
  ([& extra-info] 
    (apply util/merge-maps {:operation :get-all-invoice-items} extra-info))
  )
(defmethod execute :get-all-invoice-items [op-data] (util/get-request stripe-token (util/url-with-optional-params (str *api-root* "/invoiceitems") op-data ["count" "offset" "customer"])))