(defproject scrabbli-clj "0.1.0-SNAPSHOT"
  :description "A Clojure-based scrabble solver"
  :url "www.scrabb.li"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;;[noir "1.2.2"]
                 [clojure-lanterna "0.9.3"]]
  :main scrabbli-clj.core
  :min-lein-version "2.0.0"
  :source-paths      ["src/clojure"]
  :java-source-paths ["src/java"]
  :javac-options     ["-target" "1.6" "-source" "1.6"])
