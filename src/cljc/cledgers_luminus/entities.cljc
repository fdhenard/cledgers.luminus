(ns cledgers-luminus.entities)

(def entities [{:name "cledgers-user"
                :fields [{:name "username"
                          :type :character
                          :max-length 30
                          :unique true}
                         {:name "first-name"
                          :type :character
                          :max-length 30}
                         {:name "last-name"
                          :type :character
                          :max-length 30}
                         {:name "email"
                          :type :character
                          :max-length 30}
                         {:name "is-admin"
                          :type :boolean
                          :default false}
                         {:name "last-login"
                          :type :date-time
                          :null true}
                         {:name "is-active"
                          :type :boolean
                          :default false}
                         {:name "pass"
                          :type :character
                          :max-length 300}
                         ]}
               {:name "xaction"
                :fields [{:name "uuid"
                          :type :character
                          :max-length 100
                          :unique true}
                         {:name "description"
                          :type :character
                          :max-length 250}
                         {:name "amount"
                          :type :numeric
                          :total-length 10
                          :decimal-places 2}
                         {:name "date"
                          :type :date}
                         {:name "time-created"
                          :type :date-time
                          :default :current-time}
                         {:name "created-by"
                          :type :foreign-key
                          :references :cledgers-user}]}])
