(ns naval-eng-utils.app
  (:use naval-eng-utils.csv-databuilder))

(defn -main []
  (println (process-csv "sampleReport.csv")))
