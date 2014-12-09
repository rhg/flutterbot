(ns lazybot.mongo
  "An ugly ugly ugly hack to carry on without mongo
  I need a shower now"
  (:require
    somnium.congomongo
    [somnium.congomongo.config :as config]))

(def forms
  (for [[s v] (ns-publics 'somnium.congomongo)]
    `(defn ~(symbol (name s)) [~'& args#] (if config/*mongo-config* (apply ~v args#) (println "No mongo")))))

(eval (cons 'do forms))