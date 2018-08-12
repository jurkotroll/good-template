(ns template.pages.routs
  (:require
     [reitit.frontend.easy :as rfe]
     [reitit.core :as re]
     [template.pages.frontpage :as pages.frontpage]
     [template.pages.travels :as pages.travels]
     [template.pages.travelone :as pages.travelone]
     [reitit.coercion :as rc]
     [reitit.coercion.schema :as rsc]
     [schema.core :as s]
     [reagent.core :as r]
     [re-frame.core :refer [subscribe dispatch dispatch-sync]]
     [fipp.edn :as fedn]))


(defonce match (r/atom nil))

(defn my-profile []
  (let [user (subscribe [:user])]
    [:div
     [:h1 "Email"]
     [:h2 (get-in @user [:email])]]))

(defn about-page []
  [:div
   [:h2 "About frontend"]
   [:ul
    [:li [:a {:href "http://google.com"} "external link"]]
    [:li [:a {:href (rfe/href ::foobar)} "Missing route"]]
    [:li [:a {:href (rfe/href ::item)} "Missing route params"]]]])

(defn item-page [match]
  (let [{:keys [path query]} (:parameters match)
        {:keys [id]} path]
    [:div
     [:h2 "Selected item " id]
     (if (:foo query)
       [:p "Optional foo query param: " (:foo query)])]))



(defn current-page []
  [:div {:style {:margin-top "100px"}}
   [:ul
    [:li [:a {:href (rfe/href ::frontpage)} "Frontpage"]]
    [:li [:a {:href (rfe/href ::travels-page)} "List of travels"]]
    [:li [:a {:href (rfe/href ::travelone)} "Travel001"]]
    [:li [:a {:href (rfe/href ::about)} "About"]]
    [:li [:a {:href (rfe/href ::my-profile)} "My Profile"]]
    [:li [:a {:href (rfe/href ::item {:id 1})} "Item 1"]]
    [:li [:a {:href (rfe/href ::item {:id 2} {:foo "bar"})} "Item 2"]]]
   (if @match
     (let [view (:view (:data @match))]
       [view @match]))
   [:pre (with-out-str (fedn/pprint @match))]])

(def routes
  (re/router
    ["/"
     [""
      {:name ::frontpage
       :view pages.frontpage/frontpage}]
     ["travels"
      {:name ::travels-page
       :view pages.travels/travels-page}]
     ["travelone"
      {:name ::travelone
       :view pages.travelone/travel}]
     ["about"
      {:name ::about
       :view about-page}]
     ["profile"
      {:name ::my-profile
       :view my-profile}]
     ["item/:id"
      {:name ::item
       :view item-page
       :parameters {:path {:id s/Int}
                    :query {(s/optional-key :foo) s/Keyword}}}]]
    {:compile rc/compile-request-coercers
     :data {:coercion rsc/coercion}}))

(defn init-routs []
  (rfe/start! routes
              (fn [m] (reset! match m))
              {:use-fragment true
               :path-prefix "/"}))
