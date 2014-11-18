(ns game-of-life.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn tick
  "Increments the game state"
  [& cells]
  [])


;; each cell combination
;; test if a cell is a neighbor of another cell
;; if it is increment a counter
;; iterate through counter hash
;; if < 2 it dies
;; if > 2 it lives
