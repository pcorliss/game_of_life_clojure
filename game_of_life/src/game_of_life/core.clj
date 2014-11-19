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

(defn neighbor-counter
  "takes a hash keyed by coords and increments based on neighbor pairs"
  [acc cells]
  (let [[cell1 cell2] cells]
  (if (neighbor? cell1 cell2)
    (incr_cell_count
      (incr_cell_count acc cell1)
      cell2)
    acc)))

(defn survive?
  "applies game of life rules to a cell"
  [cell]
  (let [cell-neighbor-count (val cell)]
  (and
    (<  cell-neighbor-count 4)
    (>= cell-neighbor-count 2 ))))

(defn birth?
  "decides whether a new cell should be created"
  [cell]
  (let [count (val cell)]
  (= count 3)))

(defn cell-neighbor-counter
  "takes a cell and increments a counter for all of its neighbors"
  [acc cell]
  (reduce
    incr_cell_count
    acc
    (cell-neighbors cell)))

(defn tick
  "Increments the game state"
  [& cells]
    (concat
      (keys
        (filter
          birth?
          (reduce
            cell-neighbor-counter
            {}
            cells)))
      (keys
        (filter
          survive?
          (reduce
            neighbor-counter
            {}
            (or (combo/combinations cells 2) [] ))))))
