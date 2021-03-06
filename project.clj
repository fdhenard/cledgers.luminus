(defproject cledgers-luminus "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[bouncer "1.0.1"]
                 [buddy "2.0.0"]
                 ;; [cider/cider-nrepl "0.15.0-SNAPSHOT"]
                 ;; [cider/cider-nrepl "0.14.0"]
                 [cider/cider-nrepl "0.21.1"]
                 [cljs-ajax "0.8.0"]
                 [compojure "1.6.1"]
                 [conman "0.8.3"]
                 [cprop "0.1.13"]
                 [luminus-immutant "0.2.5"]
                 [luminus-migrations "0.6.4"]
                 [luminus-nrepl "0.1.5"]
                 [markdown-clj "1.0.7"]
                 [metosin/ring-http-response "0.9.1"]
                 [mount "0.1.16"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.516" :scope "provided"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.postgresql/postgresql "42.2.5"]
                 [org.webjars.bower/tether "1.4.4"]
                 [org.webjars/bootstrap "4.2.1"]
                 [org.webjars.npm/bulma "0.7.4"]
                 ;; [org.webjars/bootstrap "4.2.1"]
                 ;; [org.webjars/font-awesome "5.7.1"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [re-frame "0.10.6"]
                 [reagent "0.8.1"]
                 [reagent-utils "0.3.2"]
                 [ring-middleware-format "0.7.3"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-defaults "0.3.2"]
                 [secretary "1.2.3"]
                 [selmer "1.12.6"]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"]
                 [venantius/accountant "0.2.4"]
                 ;; [cljsjs/react-bootstrap "0.32.4-0" :exclusions [cljsjs/react cljsjs/bootstrap]]
                 #_[declarative-ddl "0.1.1-SNAPSHOT"]
                 [com.andrewmcveigh/cljs-time "0.5.2"]
                 [clojure.java-time "0.3.2"]
                 [honeysql "0.9.8"]
                 ]

  :min-lein-version "2.0.0"

  :jvm-opts ["-server" "-Dconf=.lein-env"]
  :source-paths ["src/clj" "src/cljc"]
  :resource-paths ["resources" "target/cljsbuild"]
  :target-path "target/%s/"
  :main cledgers-luminus.core
  :migratus {:store :database :db ~(get (System/getenv) "DATABASE_URL")}

  :plugins [[lein-cprop "1.0.1"]
            [migratus-lein "0.4.3"]
            [lein-cljsbuild "1.1.4"]
            [lein-immutant "2.1.0"]]
  :clean-targets ^{:protect false}
  [:target-path [:cljsbuild :builds :app :compiler :output-dir] [:cljsbuild :builds :app :compiler :output-to]]
  :figwheel
  {:http-server-root "public"
   :nrepl-port 7002
   :css-dirs ["resources/public/css"]
   :nrepl-middleware
   [cemerick.piggieback/wrap-cljs-repl cider.nrepl/cider-middleware]}
  

  :profiles
  {:uberjar {:omit-source true
             :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
             :cljsbuild
             {:builds
              {:min
               {:source-paths ["src/cljc" "src/cljs" "env/prod/cljs"]
                :compiler
                {:output-to "target/cljsbuild/public/js/app.js"
                 :optimizations :advanced
                 :pretty-print false
                 :closure-warnings
                 {:externs-validation :off :non-standard-jsdoc :off}
                 :externs ["react/externs/react.js"]}}}}
             
             
             :aot :all
             :uberjar-name "cledgers-luminus.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:dependencies [[prone "1.6.1"]
                                 [ring/ring-mock "0.3.2"]
                                 [ring/ring-devel "1.7.1"]
                                 [pjstadig/humane-test-output "0.9.0"]
                                 [binaryage/devtools "0.9.10"]
                                 [com.cemerick/piggieback "0.2.2"]
                                 [doo "0.1.11"]
                                 [figwheel-sidecar "0.5.18"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.18.1"]
                                 [lein-doo "0.1.7"]
                                 [lein-figwheel "0.5.18"]
                                 ;; [org.clojure/clojurescript "1.10."]
                                 ]
                  :cljsbuild
                  {:builds
                   {:app
                    {:source-paths ["src/cljs" "src/cljc" "env/dev/cljs"]
                     :compiler
                     {:main "cledgers-luminus.app"
                      :asset-path "/js/out"
                      :output-to "target/cljsbuild/public/js/app.js"
                      :output-dir "target/cljsbuild/public/js/out"
                      :source-map true
                      :optimizations :none
                      :pretty-print true}}}}
                  
                  
                  
                  :doo {:build "test"}
                  :source-paths ["env/dev/clj" "test/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:resource-paths ["env/test/resources"]
                  :cljsbuild
                  {:builds
                   {:test
                    {:source-paths ["src/cljc" "src/cljs" "test/cljs"]
                     :compiler
                     {:output-to "target/test.js"
                      :main "cledgers-luminus.doo-runner"
                      :optimizations :whitespace
                      :pretty-print true}}}}
                  
                  }
   :profiles/dev {}
   :profiles/test {}})
