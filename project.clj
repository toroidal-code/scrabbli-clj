

(defproject scrabbli-clj "0.1.0"
  :description "A Clojure-based scrabble solver"
  :url "www.scrabb.li"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.googlecode.lanterna/lanterna "2.1.5"]]
  :main scrabbli-clj.core
  :min-lein-version "2.0.0"
  :source-paths      ["src/clojure"]
  :java-source-paths ["src/java"]
  :javac-options     ["-target" "1.8" "-source" "1.8"])
