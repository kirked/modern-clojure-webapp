(ns app.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

;;=======================================
;; App initialisation.

(defn mount-root
  "Mount the root components."
  []
  (reagent/render [view.initial/component]
    (.getElementById js/document "app")))


(defn ^:export init
  "Initialises the application."
  []
  (re-frame/dispatch-sync [:initialise-app])
  (mount-root)
  (re-frame/dispatch-sync [:refresh-session]))


;;=======================================
;; Re-frame utilities.

(def on-each-message
  ^:doc "Middleware processing for re-frame handlers."
  [
    (when ^boolean goog.DEBUG re-frame/debug)
  ])


(defn attribute-sub
  "Handle subscriptions to an attribute path."
  [attr-path]
  (re-frame/register-sub (keyword (first attr-path))
    (fn [db]
      (reaction (get-in @db attr-path)))))
