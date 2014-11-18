(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest game-of-life
  (testing "1 is 1"
    (is (= 1 1)))

  ;Any live cell with less than two live neighbours dies, as if caused by under-population.
  (testing "an empty game board returns an empty board"
    (is (= (tick ) [] )))

  (testing "a single cell dies with no neighbors"
    (is (= (tick {:x 1 :y 1} ) [] )))

  (testing "a single cell dies with one neighbor"
    (is (= (tick {:x 1 :y 1} {:x 0 :y 0} ) [] )))

  ;(testing "a single cell lives with two neighbors"
    ;(is (= (tick {:x 1 :y 1} {:x 0 :y 0} {:x 2 :y 2} ) [{:x 1 :y 1}] )))

  (testing "cell neighbors"
    (is (= (neighbor? {:x  1 :y -1} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x  1 :y  0} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x  1 :y  1} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x  0 :y -1} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x  0 :y  1} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x -1 :y -1} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x -1 :y  0} {:x 0 :y 0}) true))
    (is (= (neighbor? {:x -1 :y  1} {:x 0 :y 0}) true)))

  (testing "non-neighbors"
    (is (= (neighbor? {:x  0 :y  0} {:x 0 :y 0}) false))
    (is (= (neighbor? {:x  0 :y  0} {:x 2 :y 0}) false))
    (is (= (neighbor? {:x  0 :y  0} {:x 0 :y 2}) false)))
  ;Any live cell with two or three live neighbours lives on to the next generation.
  ;Any live cell with more than three live neighbours dies, as if by overcrowding.
  ;Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
  )
