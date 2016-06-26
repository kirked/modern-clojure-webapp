(ns app.util
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

;;=======================================
;; General utilities.

(defn log-info
  "Console logging - informational."
  [s & ss]
  (.info js/console (apply str (into [s] ss))))


(defn by-id
  [id]
  (.getElementById js/document id))


;;=======================================
;; Re-frame utilities.

(def on-each-message
  "Middleware processing for re-frame handlers."
  [
    (when ^boolean goog.DEBUG re-frame/debug)
  ])


(defn attribute-sub
  "Handle subscriptions to an attribute path."
  [attr-path]
  ;(log-info "registering " (first attr-path))
  (re-frame/register-sub (keyword (first attr-path))
    (fn [db]
      (reaction (get-in @db attr-path)))))
