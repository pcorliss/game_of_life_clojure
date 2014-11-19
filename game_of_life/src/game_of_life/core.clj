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

(defn cell-neighbors
  "returns a list of neighbors of a single cell"
  [cell]
  (let [{x :x y :y} cell]
    [
      {:x (- x 1) :y (- y 1)}
      {:x x       :y (- y 1)}
      {:x (+ x 1) :y (- y 1)}

      {:x (- x 1) :y y      }
      {:x (+ x 1) :y y      }

      {:x (- x 1) :y (+ y 1)}
      {:x x       :y (+ y 1)}
      {:x (+ x 1) :y (+ y 1)}
    ]))

(defn incr_cell_count
  "takes a cell and increments the counter"
  [acc cell]
  (merge acc {cell (+ 1 (map-get-int cell acc))}))

(defn cell-neighbor-counter
  "takes a cell and increments a counter for all of its neighbors"
  [acc cell]
  (reduce
    incr_cell_count
    acc
    (cell-neighbors cell)))

(defn survive?
  "applies game of life rules to a cell"
  [cell-neighbor-count]
    (and
      (<  cell-neighbor-count 4)
      (>= cell-neighbor-count 2 )))

(defn birth?
  "decides whether a new cell should be created"
  [cell]
  (let [count (val cell)]
  (= count 3)))

(defn tick
  "Increments the game state"
  [& cells]
  (let [neighbor-count (reduce cell-neighbor-counter {} cells) ]
    (concat
      (keys
        (filter
          birth?
          neighbor-count))
      (filter
        #(survive? (map-get-int % neighbor-count))
        cells))))
