(ns game-of-life.core
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn tick
  "Increments the game state"
  [& cells]
  (combo/combinations cells 2)
  [])

(defn neighbor?
  "Determines if two cells are neighbors"
  [cell1 cell2]
  (let  [{x1 :x y1 :y} cell1]
  (let  [{x2 :x y2 :y} cell2]
    (and
      (not
        (and
          (= x1 x2)
          (= y1 y2)))
      (<= (Math/abs (- x1 x2)) 1)
      (<= (Math/abs (- y1 y2)) 1)))))

;; each cell combination
;; test if a cell is a neighbor of another cell
;; if it is increment a counter
;; iterate through counter hash
;; if < 2 it dies
;; if > 2 it lives
