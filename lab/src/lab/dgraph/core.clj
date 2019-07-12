(ns lab.dgraph.core
  (:require [clojure.repl :refer :all]

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
                          (DgraphClient. stub))
      :else (DgraphClient. (into-array stub))
      ; :else stub
      
      )))

(defn q
  "query Dgraph"
  [{:keys [client
           qstring
           vars]}]
  (let [res (->
             (.newTransaction client)
             (.queryWithVars qstring vars))]
    (->
     (.getJson res)
     (.toStringUtf8))))


(comment
  
  Metadata$Key
  
  (Example/hello)
  
  (Example/run )
  
  (Example/prn "asd")
  
  
  (Example/main)
  
  (Example/prn)
  
  
  (.println (System/out) "hi")
  
  (def c (create-client {:with-auth-header? false
                         :hostname "server"
                         :port 9080
                         } ))
  
  ;;;
  )
