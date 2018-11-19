
(ns clue.example.util )

(defn get-env! [property] (aget (.-env js/process) property))
