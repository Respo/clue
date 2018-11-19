
Clue(in prototype)
----

Demo http://repo.respo-mvc.org/clue/

### Usage

[![Clojars Project](https://img.shields.io/clojars/v/respo/clue.svg)](https://clojars.org/respo/clue)

```edn
; [respo/clue "0.1.0"]
```

Example:

```clojure
(def comp-space
  (create-comp
   {:name :comp-space}
   (fn [[w h] state mutate!]
     (if (some? w)
       (div {:style (adorn {:display :inline-block, :width w})})
       (div {:style {:height h}})))))

(def comp-creator
  (create-comp
   {:state "",
    :name :creator,
    :mount (fn [] (.. js/document (querySelector ".box") (focus)))}
   (fn [[] state mutate!]
     (div
      {:style (adorn ui/row-middle)}
      (input
       {:className "box",
        :style (adorn ui/input),
        :placeholder "task content",
        :value state,
        :onChange (fn [event] (mutate! (get-value event)))})
      (comp-space 8 nil)
      (button
       {:style (adorn ui/button),
        :onClick (fn []
          (when (not (string/blank? state)) (dispatch! :create state) (mutate! "")))}
       "Add")))))
```

Public APIs:

```clojure
clue.core/create-comp
clue.core/adorn
clue.core/get-value

clue.core/get-state
clue.core/set-state!
clue.core/dispatch!
clue.core/register-dispatcher!

clue.core/div
clue.core/span ; and more
clue.core/tag*
```

Supported options:

* `:state`
* `:key-fn`
* `:name`
* `:mount`
* `:unmount`
* `:update`

### Workflow

Based on [calcit-workflow](https://github.com/mvc-works/calcit-workflow).

### License

MIT
