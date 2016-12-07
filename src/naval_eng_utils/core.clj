(ns naval-eng-utils.core
  (:require [clojure.java.io :as io])
  (:use clojure-csv.core))

(def csv-file
  (io/file
  (io/resource "sampleReport.csv" ))) ; load the csv from the resurces

(def csv-raw-data
  (parse-csv
  (slurp csv-file) )) ; returns a sequence of vectors

(def csv-keys (atom ()))
(def csv-data (atom ()))

(defn build-csv-keys [csv-content]
  (def temp-keys (atom ()))
  (doseq [element (nth csv-content 0)]
  (swap! temp-keys conj (keyword element)) )
  (swap! csv-keys conj (reverse (deref temp-keys))) )
  ; setups the keys from the csv file

(defn build-csv-data [csv-content]
  (doseq [element (nthrest csv-content 1)]
  (swap! csv-data conj element) ))
  ; get the content of the csv, will ignore the first row which are the key for the map

(defn -main []
  (build-csv-keys csv-raw-data)
  (build-csv-data csv-raw-data)

  (println (deref csv-keys))
  (println (deref csv-data))

  (doseq [data-set (deref csv-data)]
  (doseq [element data-set]
  (print element) (println (.indexOf data-set element)) ))
  )

; (defn -main []
;   (println (map-indexed vector csv-list))
;   (doseq [[x y z] csv-list]
;   (println (vector (keyword x), (keyword y), (keyword z)) )
;   (println x ) (println y) (println z))
;   (println csv-list))

; (defn -main []
;   (println
;     (map #(hash-map
;       (keyword (for [x (first csv-list)] [x] ))
;       ))))

; (vec
;   (rest %1 )))
; )))
; [(for [y x] [y] )] )))

; (defn -main []
;   (println
;   (map #(hash-map (keyword (first %1)) (vec (rest %1))) csv-list ))
;   (println csv-list ))
