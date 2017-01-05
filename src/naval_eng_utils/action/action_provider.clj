(ns naval-eng-utils.action.action-provider
  (:require clojure.core
    [naval-eng-utils.action.data-comparison :as compare]
    [naval-eng-utils.action.duplicate-data-check :as duplication]
    [naval-eng-utils.config :as config]))

(defn get-result
  "Provides the action results"
  [action original-report test-report]
  (cond (= action (get config/supported-action :comparison)) (compare/execute-comparison original-report test-report)
        (= action (get config/supported-action :duplication)) (duplication/execute-duplication-check original-report test-report)
        ;add conditions and actions here
        ;for new actions add also a key in the config.clj
        :else (str "The inputed action: " action " is not supported.")
))
