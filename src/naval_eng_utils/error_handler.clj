(ns naval-eng-utils.error-handler
  (:require clojure.core [naval-eng-utils.config :as config]))

(defn contains-match-keys
  [data-set keys]
  "Check if the data-set is mapped with the keys"
  (loop [[k & next-k] keys]
    (cond (not (contains? data-set (keyword k))) false
          (= next-k nil) true
          :else (recur next-k)
)))

(defn is-valid
  [data-set test-set]
  "Check if the data-set or the test-set are valid"
  (cond (or (not (contains? data-set (keyword config/main-key)))
            (not (contains? test-set (keyword config/main-key)))
            (= (contains-match-keys data-set config/match-keys) false)
            (= (contains-match-keys test-set config/match-keys) false)) false
        :else true
))

(defn error-message
  [data-set test-set]
  "Builds the error message for the data-set or test-set"
  (cond (not (contains? data-set (keyword config/main-key))) (str "Original report doesn't contain " config/main-key " key.")
        (not (contains? test-set (keyword config/main-key))) (str "Test report doesn't contain " config/main-key " key.")
        (= (contains-match-keys data-set config/match-keys) false) (str "Original report doesn't contain on of the " config/match-keys " keys.")
        (= (contains-match-keys test-set config/match-keys) false) (str "Test report doesn't contain on of the " config/match-keys " keys.")
))
