(defproject clojure-web "0.1-SNAPSHOT"
  :dependencies [
    [org.clojure/clojure "1.8.0"]                       ;; clojure, duh.
    [org.clojure/core.async "0.2.374"]                  ;; async operations
    [environ "1.0.2"]                                   ;; env var access

    ;; CLJS
    [org.clojure/clojurescript "1.9.89" :scope "provided"]
    [cljs-ajax "0.5.4"]
 
    ;; Web (client-side)
    [secretary "1.2.3"]                                 ;; client-side routing
    [cljsjs/react-with-addons "15.1.0-0"]               ;; React interface
    [reagent "0.6.0-rc" :exclusions [cljsjs/react]]     ;; React framework
    [reagent-forms "0.5.23"]                            ;; Reagent data binding
    [re-frame "0.7.0"]                                  ;; component model
    [re-com "0.8.3"]                                    ;; flexbox-based components
  
    ;; Web (server-side)
    [ring "1.6.0-beta1"]                                ;; web framework
    [compojure "1.5.1"]                                 ;; routing
    [ring/ring-defaults "0.2.1"]                        ;; default config
    [ring/ring-json "0.4.0"]                            ;; JSON request handling
    [ring-json-response "0.2.0"]                        ;; JSON responses
    [ring.middleware.logger "0.5.0"]                    ;; logging
    
    ;; Structure Manipulation
    [hiccup "1.0.5"]                                    ;; data structures as HTML
    [cheshire "5.6.2"]                                  ;; CLJ JSON
    [com.cemerick/url "0.1.1"]                          ;; URL helper
  ]

  :min-lein-version "2.6.1"

  :plugins [
    [lein-cljsbuild "1.1.3"]
    [lein-figwheel "0.5.4-4"]
    [lein-doo "0.1.6"]
  ]

  :clean-targets ^{:protect false} [
    "target"
  ]

  :figwheel {
   :css-dirs        ["src/resources/css"]
   :ring-handler    user/http-handler
   :server-logfile "log/figwheel.log"
  }

  :hooks [leiningen.cljsbuild]

  :profiles {
    :dev {
      :source-paths ["src/clj"]
      :dependencies [
        [figwheel-sidecar "0.5.4-4"]
        [binaryage/devtools "0.6.0"]
      ]
      :plugins [
        [lein-environ "1.0.3"]
      ]
    }

    :uberjar {
      :source-paths ["src/clj"]
      :aot          :all
      :prep-tasks   ["compile" ["cljsbuild" "once" "prod"]]
      :main         server.core
    }
  }

  :cljsbuild {
    :builds [
      { :id                 "dev"
        :source-paths       ["src/cljs"]
        :figwheel           true
        :compiler {
          :main             app.core
          :output-to        "target/js/app.js"
          :output-dir       "target/js/compiled"
          :asset-path       "target/js/compiled"
          :optimizations    :none
          :source-map       true
          :source-map-timestamp true
          :externs          ["externs.js"]
        }
      }

      { :id                 "prod"
        :jar                true
        :source-paths       ["src/cljs"]
        :compiler {
          :main             app.core
          :output-to        "target/js/app.js"
          :optimizations    :advanced
          :closure-defines  {goog.DEBUG false}
          :pretty-print     false
        }
      }
    ]
  }
)