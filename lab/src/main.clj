(ns main
  (:require [dev.nrepl]
            [dev.core]
            [lab.dgraph.core]
            [lab.dgraph.sample]
   

   ;
            )
  ;
  )


(defn -dev  [& args]
  (dev.nrepl/-main)
  )

(defn -main  [& args]
  (dev.nrepl/-main)
  )

(comment
  
  ; (Examples/hello)
  {:hello "world"}

  (System/getProperty "java.vm.version")
  (System/getProperty "java.version")
  (System/getProperty "java.specification.version")
  (clojure-version)

  
  ;
  )