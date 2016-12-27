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
         errors (str "")]
         (cond (not (= (get original-set (keyword mkey)) (get test-set (keyword mkey)))) (recur next-mkey (.concat errors (str "incorrect match for " mkey " key,")))
               (empty? next-mkey) (format-report errors)
               :else (recur next-mkey errors)
)))

(defn compare-data-sets
  [original-set test-set]
  (if (= (get original-set (keyword config/main-key)) (get test-set (keyword config/main-key)))
    (.concat (str "Checked " config/main-key " " (get original-set (keyword config/main-key)) ",")
      (compare-each-field original-set test-set config/match-keys))
    (str "")
))
