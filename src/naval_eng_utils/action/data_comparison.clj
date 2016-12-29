(ns naval-eng-utils.action.data-comparison
  (:require clojure.core [naval-eng-utils.config :as config]))

(defn format-report
  [report]
  (if (= 0 (count report))
    (.concat report "no error found\n")
    (.concat report "\n")
))

(defn compare-each-field
  [original-set test-set match-keys]
  (loop [[mkey & next-mkey] match-keys
         result (str "")]
         (cond (not (= (get original-set (keyword mkey)) (get test-set (keyword mkey)))) (recur next-mkey (.concat result (str "incorrect match for " mkey " key,")))
               (empty? next-mkey) (format-report result)
               :else (recur next-mkey result)
)))

(defn compare-data-sets
  [original-set test-set]
  (if (= (get original-set (keyword config/main-key)) (get test-set (keyword config/main-key)))
    (.concat (str "Checked " config/main-key " " (get original-set (keyword config/main-key)) ",")
      (compare-each-field original-set test-set config/match-keys))
    (str "")
))

(defn loop-test-report
  [data-set test-report]
  (loop [[test-set & next-test-set] test-report
         result (str "")]
         (if (empty? test-set)
             result
             (recur next-test-set (.concat result (compare-data-sets data-set test-set)
)))))

(defn loop-original-report
  [original-report test-report]
  (loop [[data-set & next-data-set] original-report
         result (str "")]
         (if (empty? data-set)
             result
             (recur next-data-set (.concat result (loop-test-report data-set test-report)
)))))

(defn execute-comparison
  [original-report test-report]
  (loop-original-report original-report test-report))
