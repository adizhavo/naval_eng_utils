(ns naval-eng-utils.csv-databuilder
  (:require [clojure.java.io :as io])
  (:use clojure-csv.core))

  (def mapped-data (hash-map))

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

  (defn process-csv
    "This process loads and parses the csv file and generates the mapped-data as a result"
    [file-name]
    (def file (csv-file file-name))
    (def raw-content (csv-raw-content file))
    (def ckey (csv-keys raw-content))
    (def data (csv-data raw-content))

    (doseq [k ckey]
      (def temp-map (hash-map k (list)))
      (def mapped-data (merge mapped-data temp-map)))

    (doseq [set data]
      (def value-of-set (list))
      (doseq [element set]
        (def key-of-set (nth ckey (.indexOf set element)))
        (def mapped-sets (get mapped-data key-of-set))
        (def new-value-of-set (flatten (merge (list element) mapped-sets)))
        (def mapped-data (assoc mapped-data key-of-set new-value-of-set)))))
