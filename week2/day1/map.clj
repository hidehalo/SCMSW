(def counts {"apple" 2 "orange" 1})
(print counts)
; FIXME
(print (+ (get counts "apple" 0) "\n"))
(print (get counts "banana" 0))
(def counts (assoc counts "banana" 1))
(def counts (assoc counts "apple" 3))
(print counts)
