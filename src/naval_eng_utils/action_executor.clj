(ns naval-eng-utils.action-executor
  (:require clojure.core [naval-eng-utils.error-handler :as error-check]
    [naval-eng-utils.action.data-comparison :as actions]
    [naval-eng-utils.config :as config]))

(defn get-action-result
  [action original-report test-report]
  (cond (= action (get config/supported-action :comparison)) (actions/execute-comparison original-report test-report)
        (= action (get config/supported-action :duplication)) (str "duplication action example.")
        ;add conditions and actions here
        :else (str "The inputed action: " action " is not supported.")
))

(defn execute
  "First checks if the reports are valid and executes the action"
  [action original-report test-report]
  (def validity-message (error-check/check-reports-validity original-report test-report))
  (if (= 0 (count validity-message))
      (get-action-result action original-report test-report)
      validity-message
))
