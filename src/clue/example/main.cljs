
(ns clue.example.main
  (:require [clue.example.schema :as schema]
            [cljs.reader :refer [read-string]]
            [clue.example.config :as config]
            ["shortid" :as shortid]
            ["vue" :as Vue]
            ["vue-hot-reload-api" :as hr-api]))

(defonce *app-instance (atom nil))

(def page-options
  {:el ".app",
   :data (clj->js {:message "Hello Vue"}),
   :render (fn [h]
     (this-as
      this
      (.log js/console this)
      (h
       "div"
       (clj->js {})
       (to-array
        [(.. this -$data -message)
         (h
          "button"
          (clj->js {:on {:click (fn [] (set! (.. this -$data -message) "new"))}})
          (array "change"))]))))})

(defn main! []
  (println "App started.")
  (.install hr-api Vue false)
  (when (not (.-compatible hr-api)) (println "not compatible"))
  (Vue. (clj->js page-options))
  (.createRecord hr-api "container" (clj->js page-options)))

(def mount-target (.querySelector js/document ".app"))

(defn reload! []
  (println "Code updated.")
  (.reload hr-api "container" (clj->js page-options)))

(def ssr? (some? (js/document.querySelector "meta.respo-ssr")))
