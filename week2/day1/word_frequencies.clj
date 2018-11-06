(defn word-frequencies [words]
    (reduce (fn [counts word]
        (assoc counts word (inc (get counts word 0)))) {} words
    )
)

(print (word-frequencies ["one" "potato" "two" "potato" "three" "potato" "four"]))

(print (frequencies ["one" "potato" "two" "potato" "three" "potato" "four"]))

(defn get-words [text] (re-seq #"\w+" text))

(print (get-words "one two three four five six seven eight nine ten"))

(print (map get-words ["one two three" "four five six" "seven eight nine ten"]))

(print (mapcat get-words ["one two three" "four five six" "seven eight nine ten"]))

(defn count-words-sequential [pages]
    (frequencies (mapcat get-words pages))
)