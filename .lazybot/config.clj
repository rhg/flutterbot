(let [plugins #{"dictionary" "lmgtfy" "google" "eball" "utils" "leet" "clojure"
                "login" "log" "brainfuck" "whatis" "shorturl"
                "mail" "timer" "rss" "title" "operator" "seen" "sed" "help"
                "load" "embedded" "karma" "fortune" "yesno" "autoreply" "weather" "unix-jokes"}]
  {:servers ["irc.freenode.net" "quebec.androidarea51.com"]        ; A list of servers.
   :github ["https://github.com/rhg/flutterbot" "https://github.com/teamgelato/android_device_lge_vm701"]
   :prepends #{"@"}   ; The character you want for a prepend. Currently set to @
   :weather {:token ""} ; Wunderground token.
   :dictionary {:wordnik-key "99c266291da87b231f40a0c8902040da0b568588c25526cff"} ; Wordnik API key.
   :sed {:automatic? true}
   :max-operations 3 ; The maximum number of operations that can be running at any given time.
   :pending-ops 0    ; The number of operations running right now
   :prefix-arrow "\u21D2 "
   :help {:admin-add? true  ; only admins can add help topics
          :admin-rm? true}   ; only admins can remove help topics
   :clojure {:eval-prefixes {:defaults ["->" "," ; prefixes in any channel
                                        #"&\|(.*?)(?=\|&|\|&|$)" ; stuff like &|this|&
                                        #"##(([^#]|#(?!#))+)\s*((##)?(?=.*##)|$)"]
                             ;; list of prefixes NOT to use in certain channels
                             "#tempchan" ["->"]   ; turn this off for testing
                             "#clojure" [","]}}    ; let clojurebot have this one
   :servers-port 8080                  ; port for plugins that require a webserver
   "quebec.androidarea51.com" {:channels ["#staffhome"]
                               :bot-name "rbot"
                               :users {"rhg135" {:privs :admin}}
                               :plugins plugins}

   "irc.freenode.net" {:channels ["##flutterbot" "##bronycorner"]
                       :bot-name "flutterbot"
                       :sed {:blacklist #{"#teamgelato"}}
                       :title {:automatic?  true}
                       :plugins plugins}})

; users is a series of username to password and privileges.
; plugins is a list of plugins to load at startup.
