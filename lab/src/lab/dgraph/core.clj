(ns lab.dgraph.core
  (:require [clojure.repl :refer :all]
            [cheshire.core :as json]
            [clojure.pprint :as pp]
            [clojure.reflect :refer :all]

   ;
            )
  (:import (lab.dgraph Example)
           (io.grpc ManagedChannel ManagedChannelBuilder Metadata
                    Metadata$Key)
           (io.grpc.stub MetadataUtils)
           (io.dgraph DgraphClient DgraphGrpc
                      Transaction  DgraphGrpc$DgraphStub
                      DgraphProto$Mutation DgraphProto$Operation
                      DgraphProto$Response)
           (com.google.gson Gson)
           (com.google.protobuf ByteString)
   ;
           )
  ;
  )

(defn create-client
  "create DgraphClient"
  [{:keys [with-auth-header?
           hostname
           port]}]
  (let [ch   (->
              (ManagedChannelBuilder/forAddress hostname port)
              (.usePlaintext true)
              (.build))
        stub (DgraphGrpc/newStub ch)]
    (cond
      with-auth-header? (let [md   (->
                                    (Metadata.)
                                    (.put
                                     (Metadata$Key/of "auth-token" Metadata/ASCII_STRING_MARSHALLER)
                                     "the-auth-token-value"))
                              stub (MetadataUtils/attachHeaders stub md)]
                          (DgraphClient. (into-array [stub])))
      :else (DgraphClient. (into-array [stub]))
      ; :else stub
      
      )))

(defn q-res
  "returns a Response protocol buffer object "
  [{:keys [client
           qstring
           vars]}]
  (let [res (->
             (.newTransaction client)
             (.queryWithVars qstring vars))]
    res))

(defn res->str
  "Returns Response protobuf object to string"
  [res]
  (->
   (.getJson res)
   (.toStringUtf8)))

(defn q
  "Queries Dgraph"
  [opts]
  (->
   (q-res opts)
   (res->str)))


(defn prn-members
  "Prints unique members of an instance using clojure.reflect"
  [inst]
  (->>
   (reflect inst)
   (:members)
   (sort-by :name)
   (map #(:name %))
   (set)
   (into [])
   (sort)
   pp/pprint
  ;  (pp/print-table )
  ;  (pp/print-table [:name :flags :parameter-types])
   ))



(comment

  Metadata$Key

  (Example/hello)

  (Example/run)

  (Example/prn "asd")


  (Example/main)

  (Example/prn)


  (.println (System/out) "hi")

  (def c (create-client {:with-auth-header? false
                         :hostname          "server"
                         :port              9080}))

  (def qstring (str "query all($a: string){\n"  "all(func: eq(name, $a)) {\n"  "    name\n"  "  }\n"  "}"))

  (->
   (q {:client  c
       :qstring qstring
       :vars    {"$a" "Alice"}})
   json/parse-string)
  
  (def res (->
            (q-res {:client  c
                    :qstring qstring
                    :vars    {"$a" "Alice"}})))
  
  (->>
   (reflect res)
   (:members)
   (sort-by :name)
   (map #(:name %))
   (set)
   (into [])
   (sort)
   pp/pprint
  ;  (pp/print-table )
  ;  (pp/print-table [:name :flags :parameter-types])
   )

  (prn-members res)
  
  (sort ["a" "c" "b"])
  
  (->
   (.getTxn res)
  ;  (.toStringUtf8)
   prn-members
   )
  
  (def qstring2 "query all($a: string) {
     all(func: eq(name, $a)) {
     name
     }
    }")
  
  
  
  (->
   (q {:client  c
       :qstring qstring2
       :vars    {"$a" "Alice"}})
   json/parse-string)
  
  
  ;;;
  )


(defn mutate
  "Transact dgraph mutation"
  [{:keys [data client]}]
  (let [txn (.newTransaction client)]
    (try
      (let [mu  (->
                 (DgraphProto$Mutation/newBuilder)
                 (.setSetJson (ByteString/copyFromUtf8 (json/generate-string data)))
                 (.build))]
        (.mutate txn mu)
        (.commit txn)
        )
      (catch Exception e (str "caught exception: " (.getMessage e)))
      (finally (.discard txn)))
    ;
    ))


(comment
  
  (mutate {:data {"name" "John"}
           :client c
           })
  
  ;;;
  )