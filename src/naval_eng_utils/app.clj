(ns naval-eng-utils.app
  (:use naval-eng-utils.csv-databuilder))

(defn -main []
  (process-csv "sampleReport.csv")
  (println mapped-data))
