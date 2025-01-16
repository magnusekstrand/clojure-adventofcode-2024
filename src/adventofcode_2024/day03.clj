(ns adventofcode-2024.day03)

;; re-seq returns ([mul(2,4) 2 4] [mul(5,5) 5 5] [mul(11,8) 11 8] [mul(8,5) 8 5])
(defn- find-muls
  [input]
  (re-seq #"mul\((\d{1,3}),(\d{1,3})\)" (apply str input)))

;; calculate mul -> x * y
;; input should [mul(2,4) 2 4]
(defn- calc-muls
  [input]
  (if input
    (let [x (Integer/parseInt (second input))
          y (Integer/parseInt (last input))]
      (* x y))))

;; input -> xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
;; result -> 161
(defn part-1
  "Day 03 Part 1"
  [input]
  (->> input
       (find-muls)
       (map calc-muls)
       (reduce +)))

;; input ->
;; xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
(defn- find-muls-2
  [input]
  (re-seq #"do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)" (apply str input)))

;; input ->
;; ([mul(2,4) 2 4 nil] [don't() nil nil nil] [mul(5,5) 5 5 nil] [mul(11,8) 11 8 nil] [do() nil nil do()] [mul(8,5) 8 5 nil])
(defn- remove-nils
  [input]
  (clojure.walk/postwalk
    #(if (coll? %)
       (into (empty %) (remove nil? %))
       %) input))

;; input ->
;; ([mul(8,5) 8 5] [do() do()] [mul(11,8) 11 8] [mul(5,5) 5 5] [don't()] [mul(2,4) 2 4])
(defn- filter-muls
  [input]
  (loop [[x & xs] input
         use true
         values ()]
    (if (not (nil? x))
      (cond
        (= "do()" (first x)) (recur xs true values)
        (= "don't()" (first x)) (recur xs false values)
        :else (if use
                (recur xs use (cons x values))
                (recur xs use values)))
      (identity values)
      )
    ))

;; input -> xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
;; result -> 48
;; NOTE: on training data (above) the calculation is correct,
;;       but at the real test data the calculated value is incorrect (too low)
(defn part-2
  "Day 03 Part 2"
  [input]
  (->> input
       (find-muls-2)
       (remove-nils)
       (filter-muls)
       (map calc-muls)
       (reduce +)))

