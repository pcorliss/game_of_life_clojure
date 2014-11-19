(ns game-of-life.core-spec
  (:require [speclj.core :refer :all]
            [game-of-life.core :refer :all]))

(describe "GameOfLife"
  (it "an empty game board returns an empty board"
    (should= [] (tick []) ))

  (it "a single cell dies with no neighbors"
    (should= [] (tick [{:x 1 :y 1}] )))

  (it "a single cell dies with one neighbor"
    (should= [] (tick [{:x 1 :y 1} {:x 0 :y 0}] )))

  (it "a single cell lives with two neighbors"
    (should= [{:x 1 :y 1}] (tick [{:x 1 :y 1} {:x 0 :y 0} {:x 2 :y 2}])))

  (it "a single cell lives with three neighbors"
    (should-contain {:x 1 :y 1} (tick [{:x 1 :y 1} {:x 0 :y 2} {:x 0 :y 0} {:x 2 :y 2}])))

  (it "a single cell dies with four neighbors"
    (should-not-contain {:x 1 :y 1} (tick [{:x 1 :y 1} {:x 0 :y 2} {:x 2 :y 0} {:x 0 :y 0} {:x 2 :y 2}])))

  (it "a single cell springs to life if it has three neighbors"
    (should= [{:x 1 :y 1}] (tick [{:x 2 :y 0} {:x 0 :y 0} {:x 2 :y 2}])))
  )

(run-specs)
