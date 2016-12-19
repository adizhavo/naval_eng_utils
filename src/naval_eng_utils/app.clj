(ns naval-eng-utils.app
  (:use naval-eng-utils.csv-databuilder
        [naval-eng-utils.action-provider :as reporter]))

(defn -main []
  (println (reporter/execute (process-csv "sampleReport.csv")
                             (process-csv "sampleReport1.csv")
)))
