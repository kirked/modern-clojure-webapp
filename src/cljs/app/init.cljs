(ns app.init
  (:require [re-frame.core :as re-frame]
            [app.util :refer [attribute-sub on-each-message]]))

;;=======================================
;; Initialise the application state.

(attribute-sub [:session])

(re-frame/register-handler
  :initialise-app
  on-each-message
  (fn [db _]
    (re-frame/dispatch [:app-initialised])
    ;; Here you initialise the default client-side state for your app.
    (assoc db :session {})))
