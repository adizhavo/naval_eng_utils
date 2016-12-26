(ns naval-eng-utils.config
  (:require clojure.core))

;file names config
(def file-names-config {
  :original "sampleReport.csv"
  :test "sampleReport1.csv"
})

(def original-file (get file-names-config :original))
(def test-file (get file-names-config :test))
;------

;Key search and action config
(def action-config {
  :main-key (keyword "PIPING_CODE")
  :match-keys (list
    (keyword "DN")
    (keyword "ZONA")
)})
;------

(def main-key (get action-config :main-key))
(def match-keys (get action-config :match-keys))

;Data builder default messages
(def default-data {
  :empty_key "empty key"
  :empty_cell "n/a"
})

(def empty-key (get default-data :empty-key))
(def empty-cell (get default-data :empty-cell))
;------
