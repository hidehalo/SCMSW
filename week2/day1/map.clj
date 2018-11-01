(def counts {"apple" 2 "orange" 1})
(print counts "\n")
; FIXME
(print (get counts "apple" 0) "\n")
(print (get counts "banana" 0) "\n")
(def counts (assoc counts "banana" 1))
(def counts (assoc counts "apple" 3))
(print counts "\n")
