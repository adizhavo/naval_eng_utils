(ns naval-eng-utils.config
  (:require clojure.core [cheshire.core :refer :all]))

(def config-path "resources/config.json")

(def config (parse-string (slurp config-path) true))

(def original-file (get config :original))
(def test-file (get config :test))
(def output-file (get config :output))
(def action (get config :action))
(def main-key (get config :main-key))
(def match-keys (get config :match-keys))

;Data builder default messages
(def default-data {
  :empty_key "empty key"
  :empty_cell "n/a"
})

(def empty-key (get default-data :empty-key))
(def empty-cell (get default-data :empty-cell))
;------
