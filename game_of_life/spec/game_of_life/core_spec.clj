(ns game-of-life.core-spec
  (:require [speclj.core :refer :all]
            [game-of-life.core :refer :all]))

(describe "Truth"

  (it "is true"
    (should true))

  (it "is not false"
    (should-not false)))

(describe "GameOfLife"
  (it "1 is 1"
    (should (= 1 1)))

  ;Any live cell with less than two live neighbours dies, as if caused by under-population.
  (it "an empty game board returns an empty board"
    (should (= (tick ) [] )))

  (it "a single cell dies with no neighbors"
    (should (= (tick {:x 1 :y 1} ) [] )))

  (it "a single cell dies with one neighbor"
    (should (= (tick {:x 1 :y 1} {:x 0 :y 0} ) [] )))

  ;(it "a single cell lives with two neighbors"
    ;(should (= (tick {:x 1 :y 1} {:x 0 :y 0} {:x 2 :y 2} ) [{:x 1 :y 1}] )))

  (it "cell neighbors"
    (should (neighbor? {:x  1 :y -1} {:x 0 :y 0}))
    (should (neighbor? {:x  1 :y  0} {:x 0 :y 0}))
    (should (neighbor? {:x  1 :y  1} {:x 0 :y 0}))
    (should (neighbor? {:x  0 :y -1} {:x 0 :y 0}))
    (should (neighbor? {:x  0 :y  1} {:x 0 :y 0}))
    (should (neighbor? {:x -1 :y -1} {:x 0 :y 0}))
    (should (neighbor? {:x -1 :y  0} {:x 0 :y 0}))
    (should (neighbor? {:x -1 :y  1} {:x 0 :y 0})))

  (it "non-neighbors"
    (should-not (neighbor? {:x  0 :y  0} {:x 0 :y 0}))
    (should-not (neighbor? {:x  0 :y  0} {:x 2 :y 0}))
    (should-not (neighbor? {:x  0 :y  0} {:x 0 :y 2})))
  ;Any live cell with two or three live neighbours lives on to the next generation.
  ;Any live cell with more than three live neighbours dies, as if by overcrowding.
  ;Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
  )

(run-specs)
