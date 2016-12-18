(ns naval-eng-utils.csv-databuilder
  (:require [clojure.java.io :as io] [clojure.string :as str])
  (:use clojure-csv.core))

  (def error-messages {
    :empty_key "empty key"
    :empty_cell "n/a"
    })

  (defn csv-file
    [csv-name]
    (io/file
    (io/resource csv-name))) ; load the csv from the resources

  (defn csv-raw-content
    [csv]
    (parse-csv
    (slurp csv))) ; returns a sequence of vectors

  (defn csv-keys
    [csv-content]
    (for [key-candidate (nth csv-content 0)]
      (keyword
        (if (str/blank? key-candidate)
          (get error-messages :empty_key)
          key-candidate)))) ; setups the keys from the csv file

  (defn csv-data
    [csv-content]
    (for [data-piece (nthrest csv-content 1)]
      (for [data data-piece]
        (if (str/blank? data)
          (get error-messages :empty_cell)
          data)))) ; get the content of the csv, will ignore the first row which are the key for the map

  (defn process-csv
    "This process loads and parses the csv file and generates the mapped-data as a result"
    [file-name]
    (def file (csv-file file-name))
    (def raw-content (csv-raw-content file))
    (def ckeys (csv-keys raw-content))
    (def data (csv-data raw-content))
    (for [data-set data]
      (zipmap ckeys data-set)))
