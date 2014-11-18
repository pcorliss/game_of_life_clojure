(ns game-of-life.core
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn map-get-int
  "Gets a value from a map defaults to zero"
  [our-key our-map]
  (or (-> our-key our-map) 0))

(defn neighbor?
  "Determines if two cells are neighbors"
  [cell1 cell2]
  (let [{x1 :x y1 :y} cell1]
  (let [{x2 :x y2 :y} cell2]
    (and
      (not (= cell1 cell2))
      (<= (Math/abs (- x1 x2)) 1)
      (<= (Math/abs (- y1 y2)) 1)))))

(defn neighbor-counter
  "takes a hash keyed by coords and increments based on neighbor pairs"
  [acc cells]
  (let [[cell1 cell2] cells]
  (if (neighbor? cell1 cell2)
    (merge acc
      {cell1 (+ 1 (map-get-int cell1 acc))}
      {cell2 (+ 1 (map-get-int cell2 acc))})
    acc)))

(defn survive?
  "applies game of life rules to a cell"
  [cell]
  (let [cell-neighbor-count (val cell)]
  (and
    (<  cell-neighbor-count 4)
    (>= cell-neighbor-count 2 ))))

(defn tick
  "Increments the game state"
  [& cells]
  (or
    (keys
      (filter
        survive?
        (reduce
          neighbor-counter
          {}
          (or (combo/combinations cells 2) [] ))))
    ()))
