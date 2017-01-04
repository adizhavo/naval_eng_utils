(ns naval-eng-utils.config
  (:require clojure.core [cheshire.core :refer :all]))

(def config-path "resources/config.json")
(def config (parse-string (slurp config-path) true))
(def original-file (get config :original))
(def test-file (get config :test))
(def output-folder (get config :output-folder))
(def output-suffix (get config :output-suffix))
(def actions (get config :actions))
(def main-key (get config :main-key))
(def match-keys (get config :match-keys))

(def supported-action {
  :comparison "comparison"
  :duplication "duplication"
})

;Data builder default messages
(def default-data {
  :empty_key "empty key"
  :empty_cell "n/a"
})

(def empty-key (get default-data :empty-key))
(def empty-cell (get default-data :empty-cell))
