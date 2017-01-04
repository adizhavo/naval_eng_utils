(ns naval-eng-utils.action-executor
  (:require clojure.core
    [naval-eng-utils.error-handler :as error-check]
    [naval-eng-utils.action.action-provider :as action-provider]))

(defn execute
  "First checks if the reports are valid and requests the action result from the provider"
  [action original-report test-report]
  (def validity-message (error-check/check-reports-validity original-report test-report))
  (if (= 0 (count validity-message))
      (action-provider/get-result action original-report test-report)
      validity-message
))
