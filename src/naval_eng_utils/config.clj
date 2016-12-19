(ns naval-eng-utils.config
  (:require clojure.core))

(def checker-config {
  :main-key (keyword "PIPING_CODE")
  :match-keys (list
    (keyword "DN")
    (keyword "ZONA")
)})

(def main-key (get checker-config :main-key))
(def match-keys (get checker-config :match-keys))

(def default-data {
  :empty_key "empty key"
  :empty_cell "n/a"
})

(def empty-key (get default-data :empty-key))
(def empty-cell (get default-data :empty-cell))
