(defn sum [numbers]
    (reduce + numbers)
)
(print (sum [1 2 3 4 5]))

(defn sum-recur [numbers sum]
    (if (empty? numbers)
        sum
        (recur (rest numbers) (+ sum (first numbers)))
    )
)

(print "\n")

(print (sum-recur (range 10) 0))