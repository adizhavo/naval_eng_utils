(ns naval-eng-utils.config
  (:require clojure.core))

(def config {
  :original "resources/sampleReport.csv"
  :test "resources/sampleReport1.csv"
  :action "compare"
  :main-key (keyword "PIPING_CODE")
  :match-keys (list
    (keyword "DN")
    (keyword "ZONA")
)})

(def original-file (get config :original))
(def test-file (get config :test))
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
