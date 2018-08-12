(ns template.pages.travels
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    ["@material-ui/core/List" :default List]
    ["@material-ui/core/ListItem" :default ListItem]
    ["@material-ui/core/ListItemText" :default ListItemText]
    ["@material-ui/core/ListItemSecondaryAction" :default ListItemSecondaryAction]
    [reitit.frontend.easy :as rfe]
    ["@material-ui/core/Avatar" :default Avatar]
    #_["@material-ui/core/Link" :default Link]
    ["@material-ui/core/ButtonBase" :default ButtonBase]
    [fipp.edn :as fedn]))

(enable-console-print!)

(defn travel-to-list [id place]
  (let [open-travel (rfe/href ::travelone)]
    [:a {:href open-travel}
     [:> ListItem {:key id :button true :href open-travel}
      [:> Avatar (first place)]
      [:> ListItemText {:primary place}]
      [:a {:href open-travel} "Open"]
      #_[:> ListItemSecondaryAction]
      ;; [:> ButtonBase
        #_[:a {:href open-travel} "Open"]]]))




(defn list-of-travels [travels]
  (let [values (vals @travels)]

    [:> List
      (for [{:keys [travel/id travel/place]} values]
       [:div
         [travel-to-list id place]])]))




(defn travels-page []
  (let [travels (subscribe [:travels])]
   (fn []

    [:div
     [:h1 "Travels"]
     [:div
       [list-of-travels travels]
       [:pre (with-out-str (fedn/pprint (keys @travels)))]]])))
