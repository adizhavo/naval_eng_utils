(ns naval-eng-utils.action-executor
  (:require clojure.core [naval-eng-utils.error-handler :as error-check]
    [naval-eng-utils.action.data-comparison :as actions]))

(defn execute
  "Executes the configured action on two mapped csv data"
  [original-report test-report]
  (loop [[data-set & next-data-set] original-report
         [test-set & next-test-set] test-report
         result (str "")]
         (cond (or (empty? next-data-set) (empty? next-test-set)) result
               (not (error-check/is-valid data-set test-set)) (error-check/error-message data-set test-set)
               :else (recur next-data-set next-test-set
                 (.concat result (actions/compare-data-sets data-set test-set)
)))))
