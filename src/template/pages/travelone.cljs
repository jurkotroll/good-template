(ns template.pages.travelone
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    ["@material-ui/core/List" :default List]
    ["@material-ui/core/ListItem" :default ListItem]
    ["@material-ui/core/ListItemText" :default ListItemText]
    [reitit.frontend.easy :as rfe]
    ["@material-ui/core/Avatar" :default Avatar]
    ["@material-ui/core/Button" :default Button]
    [fipp.edn :as fedn]))

(defn travel []
 (let []
   (fn []
     [:div
      [:h1 "Travel001"]
      [:> Button {:on-click #(rfe/push-state ::travels-page)} "Back"]])))
