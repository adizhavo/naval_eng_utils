(ns naval-eng-utils.core
  (:require [clojure.java.io :as io])
  (:use clojure-csv.core))

(def mapped-data (hash-map))

(defn csv-file
  [csv-name]
  (io/file
  (io/resource csv-name))) ; load the csv from the resurces

(defn csv-raw-content
  [csv]
  (parse-csv
  (slurp csv))) ; returns a sequence of vectors

(defn csv-keys
  [csv-content]
  (def temp-keys (list))
  (doseq [element (nth csv-content 0)]
    (def key-element (keyword element))
    (def temp-keys (merge temp-keys key-element)))
  (reverse (flatten temp-keys))) ; setups the keys from the csv file

(defn csv-data
  [csv-content]
  (def temp-data (list))
    (doseq [element (nthrest csv-content 1)]
    (def temp-data (merge temp-data element)))
  (reverse temp-data)) ; get the content of the csv, will ignore the first row which are the key for the map
