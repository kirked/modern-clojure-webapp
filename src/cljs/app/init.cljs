(ns app.init
  (:require [re-frame.core :as re-frame]
            [app.core :refer [on-each-message attribute-sub]]))

;;=======================================
;; Initialise the application state.

(re-frame/register-handler
  :initialise-app
  on-each-message
  (fn [db _]
    (re-frame/dispatch [:app-initialised])
    ;; Here you initialise the default client-side state for your app.
    db))


;;=======================================
;; Refresh the session state.

(re-frame/register-sub :session)

(re-frame/register-handler
  :refresh-session
  on-each-message
  (fn [db _]
    (re-frame/dispatch [:session-refreshed])
    ;; Here you update the client session data for your app.
    (assoc db :session {})))