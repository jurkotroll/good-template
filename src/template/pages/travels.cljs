(ns template.pages.travels
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    ["@material-ui/core/List" :default List]
    ["@material-ui/core/ListItem" :default ListItem]
    ["@material-ui/core/ListItemText" :default ListItemText]
    ["@material-ui/core/ListItemSecondaryAction" :default ListItemSecondaryAction]
    [reitit.frontend :as rf]
    [reitit.frontend.easy :as rfe]
    [reitit.frontend.history :as rfh]
    ["@material-ui/core/Avatar" :default Avatar]
    #_["@material-ui/core/Link" :default Link]
    ["@material-ui/core/ButtonBase" :default ButtonBase]
    ["@material-ui/core/Button" :default Button]
    [fipp.edn :as fedn]))

(enable-console-print!)

(defn travel-to-list [id]
  (let [open-travel #(rfe/push-state ::travelone)]
   (fn []
    [:button {:on-click #(rfe/push-state ::frontpage)} (str "Open " id)])))

; (defn travel-to-list [id place]
;   (let []
;     [:> ListItem {:key id :button true :on-click #(rfe/push-state "travelone")}
;
;       [:> Avatar (first place)]
;       [:> ListItemText {:primary place}]
;       [:a {:href nil}
;        [:> ListItemSecondaryAction
;         [:> Button {`} "Open"]]]]))





(defn list-of-travels [travels]
  (let [values (vals @travels)]

    [:div
      (for [{:keys [travel/id #_travel/place]} values]
       [:div
         [travel-to-list id #_place]])]))




(defn travels-page []
  (let [travels (subscribe [:travels])]
   (fn []

    [:div
     [:h1 "Travels"]
     [:div
       [list-of-travels travels]
       [:pre (with-out-str (fedn/pprint (keys @travels)))]]])))
