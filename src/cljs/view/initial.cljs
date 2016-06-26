(ns view.initial
  (:require [reagent.core :as r]
            [re-frame.core :as re-frame]))


(defn component
  "The initial application view component, mounted to '#app'."
  []
  (let [session (re-frame/subscribe [:session])]
    (fn []
      [:div
        [:h3 "Hello!"]
        [:div.welcome "Welcome to your application."]
        [:div "Here is the dynamic session data:"
          [:pre (with-out-str (cljs.pprint/pprint @session))]]])))