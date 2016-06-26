(ns app.core
  (:require [reagent.core :as r]
            [re-frame.core :as re-frame]
            [app.init]
            [app.util :refer [by-id]]
            [view.initial]))

;;=======================================
;; App initialisation.

(defn ^:export init
  "Initialises the application."
  []
  (re-frame/dispatch-sync [:initialise-app]))


(defn mount-root
  "Mount the root components."
  []
  (r/render [view.initial/component] (by-id "app")))


(re-frame/register-handler
  :app-initialised
  (fn [db _]
    (mount-root)
    db))