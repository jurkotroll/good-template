(ns template.pages.travel
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    ["@material-ui/core/List" :default List]
    ["@material-ui/core/ListItem" :default ListItem]
    ["@material-ui/core/ListItemText" :default ListItemText]
    [reitit.frontend.easy :as rfe]
    ["@material-ui/core/Avatar" :default Avatar]
    ["@material-ui/core/Button" :default Button]
    [fipp.edn :as fedn]))

(defn travel [match]
 (let [travels (subscribe [:travels])
       values (vals @travels)
       {:keys [travel/id travel/place]} values]

   (fn []
     [:div
      [:h1 (str "Travel" travel/id)]
      [:> Button {:on-click #(rfe/push-state :travels/listpage)} "Back"]
      [:pre (with-out-str (fedn/pprint @match))]])))
