(def pages ["one potato two patato three patato four" "five potato six potato seven potato more"])

(defn get-words [text] (re-seq #"\w+" text))

(def merge-counts (partial merge-with +))

(defn count-words-parallel [pages]
    (reduce (partial merge-with +)
        (pmap #(frequencies (get-words %)) pages)
    )
)

(defn count-words-sequential [pages]
    (frequencies (mapcat get-words pages))
)

(defn count-words [pages]
    (reduce (partial merge-with +)
        (pmap count-words-sequential (partition-all 100 pages))
    )
)

(print (count-words pages))