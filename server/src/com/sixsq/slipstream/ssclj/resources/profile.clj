(ns com.sixsq.slipstream.ssclj.resources.profile
  (:require
    [com.sixsq.slipstream.ssclj.resources.spec.profile]
    [com.sixsq.slipstream.auth.acl :as a]
    [com.sixsq.slipstream.ssclj.resources.common.crud :as crud]
    [com.sixsq.slipstream.ssclj.resources.common.std-crud :as std-crud]
    [com.sixsq.slipstream.ssclj.resources.common.utils :as u]
    [com.sixsq.slipstream.ssclj.resources.common.schema :as c]))

(def ^:const resource-tag :profiles)
(def ^:const resource-name "Profile")
(def ^:const resource-url (u/de-camelcase resource-name))
(def ^:const collection-name "ProfileCollection")

(def ^:const resource-uri (str c/cimi-schema-uri resource-name))
(def ^:const collection-uri (str c/cimi-schema-uri collection-name))

(def collection-acl {:owner {:principal "ADMIN"
                             :type      "ROLE"}
                     :rules [{:principal "ADMIN"
                              :type      "ROLE"
                              :right     "ALL"}
                             {:principal "USER"
                              :type      "ROLE"
                              :right     "ALL"}
                             {:principal "ANON"
                              :type      "ROLE"
                              :right     "ALL"}]})

;;
;; "Implementations" of multimethod declared in crud namespace
;;

(def validate-fn (u/create-spec-validation-fn :cimi/profile))
(defmethod crud/validate
  resource-uri
  [resource]
  (validate-fn resource))

(def add-impl (std-crud/add-fn resource-name collection-acl resource-uri))
(defmethod crud/add resource-name
  [request]
  (add-impl request))

(def retrieve-impl (std-crud/retrieve-fn resource-name))
(defmethod crud/retrieve resource-name
  [request]
  (retrieve-impl request))

(def delete-impl (std-crud/delete-fn resource-name))
(defmethod crud/delete resource-name
  [request]
  (delete-impl request))

;;
;; available operations
;;

(def edit-impl (std-crud/edit-fn resource-name))
(defmethod crud/edit resource-name
  [request]
  (edit-impl request))

;;
;; collection
;;

(def query-impl (std-crud/query-fn resource-name collection-acl collection-uri resource-tag))
(defmethod crud/query resource-name
  [request]
  (query-impl request))
