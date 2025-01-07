(defproject adventofcode-2024 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
               [org.clojure/tools.cli "1.0.219"]
               [org.clojure/math.numeric-tower "0.0.4"]
               [org.clojure/math.combinatorics "0.1.6"]
               [org.clojure/core.match "1.0.1"]
               [org.clojure/data.priority-map "1.1.0"]] 
  :plugins [[lein-kibit "0.1.6"]]
  :main adventofcode-2024.core
  :repl-options {:init-ns adventofcode-2024.core})