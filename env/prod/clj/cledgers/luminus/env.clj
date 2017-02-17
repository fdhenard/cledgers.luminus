(ns cledgers.luminus.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[cledgers.luminus started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[cledgers.luminus has shut down successfully]=-"))
   :middleware identity})
