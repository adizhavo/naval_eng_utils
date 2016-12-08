(ns naval-eng-utils.core
  (:use naval-eng-utils.databuilder))

(defn -main []
  (process-csv "sampleReport.csv")
  (println mapped-data))
