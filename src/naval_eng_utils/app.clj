(ns naval-eng-utils.app
  (:require [naval-eng-utils.config :as config])
  (:use naval-eng-utils.csv-databuilder
        [naval-eng-utils.action-executor :as op])
  (:gen-class))

(def original-report (doall (build-csv-hashmap config/original-file)))
(def test-report (doall (build-csv-hashmap config/test-file)))

(defn -main []
  (loop [[action & next-action] config/actions]
    (if (empty? action) 0
      (do
        (spit (str config/output-folder action config/output-suffix) 
          (op/execute action original-report test-report))
        (recur next-action)
))))
