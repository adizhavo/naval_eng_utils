(ns naval-eng-utils.action-executor
  (:require clojure.core [naval-eng-utils.error-handler :as error-check]))

(defn execute
  "Executes the configured action on two mapped csv data"
  [original-report test-report]
  (loop [[data-set & next-data-set] original-report
         [test-set & next-test-set] test-report
         result []
         break false]
         (cond (or (= break true) (empty? next-data-set) (empty? next-test-set)) result
               (not (error-check/is-valid data-set test-set)) (error-check/error-message data-set test-set)
               ;polymorphic dispatch to the configured action
               :else (recur next-data-set next-test-set result break)
)))
