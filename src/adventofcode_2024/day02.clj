(ns adventofcode-2024.day02
  (:require [adventofcode-2024.utils :as u]))

(defn is-increasing?
  "Find levels that are all increasing"
  [xs]
  (apply < xs))

(defn is-decreasing?
  "Find levels that are all decreasing"
  [xs]
  (apply > xs))

(defn ordered?
  [xs]
  (or (is-increasing? xs) (is-decreasing? xs)))

(defn find-good-gaps?
  "Find reports with adjacent levels differ by at least one and at most three"
  [xs]
  (let [pairs (partition 2 1 xs)]
    (every? #(and (>= % 1) (<= % 3))
            (map #(abs (- (first %) (last %))) pairs))))

(defn part-1
  "Day 02 Part 1"
  [input]
  (->> input
       u/to-lines
       (map u/parse-out-longs)
       (filter ordered?)
       (filter find-good-gaps?)
       count))

(defn drop-nth
  [l n]
  (concat (take n l) (drop (inc n) l)))

(defn get-report-variants
  [report]
  (let [size (count report)]
    (loop [[n & ns] (range size), result '()]
      (if (nil? n)
        result
        (recur ns
               (conj result (drop-nth report n)))))))

(defn get-distinct
  [l]
  (distinct l))

(defn get-all-variants
  [reports]
  (let [size (count reports)]
    (loop [[n & ns] (range size), result (list reports)]
      (if (nil? n)
        result
        (recur ns
          (conj result
                (get-distinct (get-report-variants (nth reports n)))))))))

(defn find-some-variant?
  [variants]
  (some #(and (ordered? %) (find-good-gaps? %)) variants))

(defn part-2
  "Day 02 Part 2"
  [xs]
  (->> xs
       u/to-lines
       (map u/parse-out-longs)
       (get-all-variants)
       (filter find-some-variant?)
       (count)
       (- 1)
       (abs)))
