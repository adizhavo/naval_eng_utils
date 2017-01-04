(ns naval-eng-utils.app
  (:require [naval-eng-utils.config :as config])
  (:use naval-eng-utils.csv-databuilder
        [naval-eng-utils.action-executor :as op])
  (:gen-class))

(def original-report (doall (build-csv-hashmap config/original-file)))
(def test-report (doall (build-csv-hashmap config/test-file)))

(defn -main []
  (spit config/output-file (op/execute config/action original-report test-report)))
