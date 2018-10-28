(require '[clojure.core.reducers :as r])

(defn sum [numbers]
    (reduce + numbers)
)

(defn parallel-sum [numbers]
    (r/fold + numbers)
)

(def numbers (into [] (range 0 10000000)))

(time (sum numbers))

(time (parallel-sum numbers))