(ns template.pages.travels
  (:require
    [re-frame.core :refer [subscribe dispatch]]))

(enable-console-print!)

(defn travel-from-list [travel-from-list]
  (let [[_ body] travel-from-list
        {:travel/keys [place id]} body]
    (println (str id " | "  " | " place))
    [:il
      [:h4 id] [:h4 place]]))

(defn travels-page []
  (let [travels (subscribe [:travels])]
    [:div
     [:h1 "Travels"]
     [:div (println travels)
      [:li
       (map travel-from-list @travels)]]]))
