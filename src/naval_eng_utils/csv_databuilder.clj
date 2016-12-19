(ns naval-eng-utils.csv-databuilder
  (:require [clojure.java.io :as io] [clojure.string :as str])
  (:use clojure-csv.core))

(def default-data {
  :empty_key "empty key"
  :empty_cell "n/a"
})

(def empty-key (get default-data :empty-key))
(def empty-cell (get default-data :empty-cell))

(defn csv-file
  "load the csv file from the resources"
  [csv-name]
  (io/file
  (io/resource csv-name)
))

(defn csv-raw-content
  "returns a sequence of vectors from the csv file"
  [csv]
  (parse-csv
  (slurp csv)
))

(defn csv-keys
  "setups the keys from the csv file"
  [csv-content]
  (for [key (nth csv-content 0)]
    (keyword
      (if (str/blank? key)
        empty-key
        key)
)))

(defn csv-data
  "get the content of the csv, will ignore the first row which are the key for the map"
  [csv-content]
  (for [data-set (nthrest csv-content 1)]
    (for [cell data-set]
      (if (str/blank? cell)
        empty-cell
        cell)
)))

(defn process-csv
  "This process loads and parses the csv file and generates the mapped-data as a result"
  [file-name]
  (def file (csv-file file-name))
  (def raw-content (csv-raw-content file))
  (def ckeys (csv-keys raw-content))
  (def data (csv-data raw-content))
  (for [data-set data]
    (zipmap ckeys data-set)
))
