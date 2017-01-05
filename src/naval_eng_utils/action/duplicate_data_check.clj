(ns naval-eng-utils.action.duplicate-data-check
  (:require clojure.core [naval-eng-utils.config :as config]))

(defn check-for-duplication
  "Second loop throught all the data set and checks if a duplication happens"
  [report report-name data-to-check data-to-check-index]
  (loop [[check-data-set & next-check-data-set] report]
         (cond (empty? check-data-set) (str report-name " doesnt have duplicate data for the " config/main-key " key\n")
               (and (= (get check-data-set (keyword config/main-key)) data-to-check)
                    (not (= (.indexOf report check-data-set) data-to-check-index))) (str "Duplicate data detected for the " config/main-key " key in the " report-name " report\n")
               :else (recur next-check-data-set)
)))

(defn check-report
  "First loop throught each data set"
  [report report-name]
  (loop [[data-set & next-data-set] report
         result (str "")]
         (if (empty? data-set) result
         (recur next-data-set
           (.concat result (check-for-duplication
                                      report
                                      report-name
                                      (get data-set (keyword config/main-key))
                                      (.indexOf report data-set)
))))))

(defn execute-duplication-check
  "Returns the result if the original or test report have duplicate key data"
  [original-report test-report]
  (.concat (check-report original-report "Original") (check-report test-report "Test")
))
