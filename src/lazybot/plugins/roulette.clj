(ns lazybot.plugins.roulette
  (:use [lazybot.registry]
        [irclj.core :refer [kick]]))

(def dead (atom #{}))

(defn make-pistol [size n k]
  "Returns a random seq of false and true of len size with n true"
  (shuffle (concat (take k (repeat true)) (take (- size n k) (repeat false)) (take n (repeat nil)))))

(def pistol (atom []))

(defn load-pistol [com-m msg & args]
  "Assigns (make-pistol ...) to pistol atom"
  (reset! pistol (apply make-pistol args))
  (send-message com-m (str msg "."))
  (reset! dead #{}))

(defn pull [{:keys [nick com channel] :as com-m}]
  "Pulls the trigger"
  ;Bullet check
  (when (seq @pistol)
    ;Check if dead
    (if (@dead nick)
      ;They are
      (send-message com-m (str "You are dead, " nick))
      ;They are alive
      (do
        (case (peek @pistol)
          nil (do
                (send-message com-m (str "drags away " nick "'s body"))
                (swap! dead conj nick))
          false (send-message com-m (str nick " gets to live another day"))
          true (do
                 (send-message com-m (str "drags away " nick "'s body"))
                 (kick com channel nick :reason "BANG!")
                 (swap! dead conj nick)))
        (swap! pistol pop)))))

(defplugin

  (:cmd
    "Load pistol"
    #{"load-pistol" "rload"}
    (fn [{:keys [args] :as com-m}]
      (if-let [[size n k] args]
        (load-pistol com-m "The pistol is loaded" (Integer/parseInt size) (Integer/parseInt n) (Integer/parseInt k))
        (load-pistol com-m "The pistol is loaded" 6 1 0))))

  (:cmd
    "Loads with kick bullets"
    #{"load-lethal"}
    (fn [{:keys [com] :as com-m}]
      (load-pistol com-m "My name is flutterbot, you have loaded me, now prepare to die" 6 0 5)))

  (:cmd
    "Loops pull"
    #{"loop-pull"}
    (fn [{:keys [nick] :as com-m}]
      (when-not (@dead nick)
        (loop [com-m com-m c 0]
          (pull com-m)
          (if-not (@dead nick)
            (recur com-m (inc c))
            (send-message com-m (str "dead in " (inc c) " pulls")))))))

  (:cmd
    "Pulls the trigger"
    #{"pull"}
    #'pull))
