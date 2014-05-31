(ns similarusers.euclidean
  (:use [similarusers.data :only [critics]]
        [clojure.set :only [intersection]]
        [clojure.math.numeric-tower :only [expt]]))

(defn get_critic 
  [prefs person movie]
  (-> (get prefs person)
      (get movie)))

(defn sim_distance
  [prefs person1 person2]
  (let [getter    (partial get_critic prefs)
        movies_p1 (into #{} (keys (get prefs person1)))
        movies_p2 (into #{} (keys (get prefs person2)))
        movies    (intersection movies_p1 movies_p2)]
    (->> 
     (reduce + (map #(expt (- (getter person1 %)
                              (getter person2 %))
                           2)
                    movies))
     (+ 1)
     (/ 1))))
  


