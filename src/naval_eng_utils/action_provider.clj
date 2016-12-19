(ns naval-eng-utils.action-provider
  (:use clojure.core))

(def checker-config {
  :main-key (keyword "PIPING_CODE")
  :match-keys (list
    (keyword "DN")
    (keyword "ZONA")
)})

(def main-key (get checker-config :main-key))
(def match-keys (get checker-config :match-keys))

(defn contains-match-keys
  [data-set keys]
  (loop [[k & next-k] keys]
    (cond (not (contains? data-set k)) false
          (= next-k nil) true
          :else (recur next-k)
)))

(defn is-valid
  [data-set test-set]
  (cond (or (not (contains? data-set main-key))
            (not (contains? test-set main-key))
            (= (contains-match-keys data-set match-keys) false)
            (= (contains-match-keys test-set match-keys) false)) false
        :else true
))

(defn error-message
  [data-set test-set]
  (cond (not (contains? data-set main-key)) (str "Original report doesn't contain " main-key " key.")
        (not (contains? test-set main-key)) (str "Test report doesn't contain " main-key " key.")
        (= (contains-match-keys data-set match-keys) false) (str "Original report doesn't contain on of the " match-keys " keys.")
        (= (contains-match-keys test-set match-keys) false) (str "Test report doesn't contain on of the " match-keys " keys.")
))

(defn execute
  [original-report test-report]
  (loop [[data-set & next-data-set] original-report
         [test-set & next-test-set] test-report
         result []
         break false]
         (cond (or (= break true) (empty? next-data-set) (empty? next-test-set)) result
               (not (is-valid data-set test-set)) (error-message data-set test-set)
               ;polymorphic dispatch to the configured action
               :else (recur next-data-set next-test-set result break)
)))
