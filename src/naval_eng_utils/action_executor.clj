(ns naval-eng-utils.action-executor
  (:require clojure.core [naval-eng-utils.error-handler :as error-check]
    [naval-eng-utils.action.data-comparison :as actions]))

(defn execute
  "First checks if the reports are valid and executes the action"
  [original-report test-report]
  (def validity-message (error-check/check-reports-validity original-report test-report))
  (if (= 0 (count validity-message))
      (actions/execute-comparison original-report test-report)
      validity-message
))
