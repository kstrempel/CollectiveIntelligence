(ns similarusers.euclidean
  (:use [similarusers.data :only [critics]]
        [clojure.set :only [intersection]]
        [clojure.math.numeric-tower :only [expt]]))

(defn get_critic 
  [prefs person movie]
  (get (get prefs person) movie))

(defn sim_distance
  [prefs person1 person2]
  (let [items_p1 (into #{} (keys (get prefs person1)))
        items_p2 (into #{} (keys (get prefs person2)))
        items    (intersection items_p1 items_p2)]
    (/ 1 
       (+ 1
          (reduce + (map #(expt (- (get_critic prefs person1 %)
                                (get_critic prefs person2 %))
                                2)
                         items))))))

(defn -main
  [& args]
  (println "Hello World"))


