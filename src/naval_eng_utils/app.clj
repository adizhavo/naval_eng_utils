(ns naval-eng-utils.app
  (:require [naval-eng-utils.config :as config])
  (:use naval-eng-utils.csv-databuilder
        [naval-eng-utils.action-executor :as op]))

(defn -main []
  (println (op/execute (build-csv-hashmap config/original-file)
                       (build-csv-hashmap config/test-file)
)))
