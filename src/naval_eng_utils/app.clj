(ns naval-eng-utils.app
  (:use naval-eng-utils.csv-databuilder
            [naval-eng-utils.action-executor :as action]))

(defn -main []
  (println (action/execute (build-csv-hashmap "sampleReport.csv")
                           (build-csv-hashmap "sampleReport1.csv")
)))
