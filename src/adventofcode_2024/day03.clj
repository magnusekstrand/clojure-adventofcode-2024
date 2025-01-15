(ns adventofcode-2024.day03)

;; re-seq returns ([mul(2,4) 2 4] [mul(5,5) 5 5] [mul(11,8) 11 8] [mul(8,5) 8 5])
(defn- find-muls
  [input]
  (re-seq #"mul\((\d{1,3}),(\d{1,3})\)" (apply str input)))

;; calculate mul -> x * y
;; input is [mul(2,4) 2 4]
(defn- calc-muls
  [input]
  (if input
    (let [x (Integer/parseInt (second input))
          y (Integer/parseInt (last input))]
      (* x y))))


(defn part-1
  "Day 03 Part 1"
  [input]
  (->> input
       (find-muls)
       (map calc-muls)
       (reduce +)))

;(defn part-2
;  "Day 03 Part 2"
;  [input]
;  (->> input
;       u/to-lines))
