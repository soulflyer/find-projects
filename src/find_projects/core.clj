(ns find-projects.core
  (:require [clojure.tools.cli :refer :all]
            [image-lib.core    :refer :all])
  (:gen-class))

(def cli-options
  [["-c" "--count" "Counts the results"]
   ["-d" "--database DATABASE" "specifies database to use"
    :default "soulflyer"]
   ["-i" "--image-collection IMAGE-COLLECTION" "specifies the image collection"
    :default "images"]
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)
        projects (find-projects
                  (:database options)
                  (:image-collection options))]

    (cond
      (:help options)
      (println (str "Usage:\nfind-projects [options] keyword\n\nvoptions:\n" summary))
      (:count options)
      (println
       (count projects))
      :else
      (println projects))))
