(defn fib 
         ([]
           (fib 1 1))
         ([a b]
           (lazy-seq (cons a (fib b (+ a b))))))

(print (take 10 (fib)))