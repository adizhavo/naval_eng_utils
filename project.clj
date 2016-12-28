(defproject naval_eng_utils "0.0.1"
  :description "Utilities for naval engineers, handles csv manipulations, checks and reports"
  :url "https://github.com/adizhavo/naval_eng_utils"
  :dependencies [[org.clojure/clojure "1.8.0"] [clojure-csv/clojure-csv "2.0.1"] [cheshire "5.6.3"]]
  :main naval-eng-utils.app
  :profiles {:uberjar {:aot :all}}
  :aot [naval-eng-utils.app])
