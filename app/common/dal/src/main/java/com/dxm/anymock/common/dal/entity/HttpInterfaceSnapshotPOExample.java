package com.dxm.anymock.common.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpInterfaceSnapshotPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HttpInterfaceSnapshotPOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdIsNull() {
            addCriterion("http_interface_id is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdIsNotNull() {
            addCriterion("http_interface_id is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdEqualTo(Long value) {
            addCriterion("http_interface_id =", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdNotEqualTo(Long value) {
            addCriterion("http_interface_id <>", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdGreaterThan(Long value) {
            addCriterion("http_interface_id >", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("http_interface_id >=", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdLessThan(Long value) {
            addCriterion("http_interface_id <", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdLessThanOrEqualTo(Long value) {
            addCriterion("http_interface_id <=", value, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdIn(List<Long> values) {
            addCriterion("http_interface_id in", values, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdNotIn(List<Long> values) {
            addCriterion("http_interface_id not in", values, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdBetween(Long value1, Long value2) {
            addCriterion("http_interface_id between", value1, value2, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceIdNotBetween(Long value1, Long value2) {
            addCriterion("http_interface_id not between", value1, value2, "httpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriIsNull() {
            addCriterion("http_interface_request_uri is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriIsNotNull() {
            addCriterion("http_interface_request_uri is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriEqualTo(String value) {
            addCriterion("http_interface_request_uri =", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriNotEqualTo(String value) {
            addCriterion("http_interface_request_uri <>", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriGreaterThan(String value) {
            addCriterion("http_interface_request_uri >", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_request_uri >=", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriLessThan(String value) {
            addCriterion("http_interface_request_uri <", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriLessThanOrEqualTo(String value) {
            addCriterion("http_interface_request_uri <=", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriLike(String value) {
            addCriterion("http_interface_request_uri like", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriNotLike(String value) {
            addCriterion("http_interface_request_uri not like", value, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriIn(List<String> values) {
            addCriterion("http_interface_request_uri in", values, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriNotIn(List<String> values) {
            addCriterion("http_interface_request_uri not in", values, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriBetween(String value1, String value2) {
            addCriterion("http_interface_request_uri between", value1, value2, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestUriNotBetween(String value1, String value2) {
            addCriterion("http_interface_request_uri not between", value1, value2, "httpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodIsNull() {
            addCriterion("http_interface_request_method is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodIsNotNull() {
            addCriterion("http_interface_request_method is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodEqualTo(String value) {
            addCriterion("http_interface_request_method =", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodNotEqualTo(String value) {
            addCriterion("http_interface_request_method <>", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodGreaterThan(String value) {
            addCriterion("http_interface_request_method >", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_request_method >=", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodLessThan(String value) {
            addCriterion("http_interface_request_method <", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("http_interface_request_method <=", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodLike(String value) {
            addCriterion("http_interface_request_method like", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodNotLike(String value) {
            addCriterion("http_interface_request_method not like", value, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodIn(List<String> values) {
            addCriterion("http_interface_request_method in", values, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodNotIn(List<String> values) {
            addCriterion("http_interface_request_method not in", values, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodBetween(String value1, String value2) {
            addCriterion("http_interface_request_method between", value1, value2, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceRequestMethodNotBetween(String value1, String value2) {
            addCriterion("http_interface_request_method not between", value1, value2, "httpInterfaceRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionIsNull() {
            addCriterion("http_interface_description is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionIsNotNull() {
            addCriterion("http_interface_description is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionEqualTo(String value) {
            addCriterion("http_interface_description =", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionNotEqualTo(String value) {
            addCriterion("http_interface_description <>", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionGreaterThan(String value) {
            addCriterion("http_interface_description >", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_description >=", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionLessThan(String value) {
            addCriterion("http_interface_description <", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionLessThanOrEqualTo(String value) {
            addCriterion("http_interface_description <=", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionLike(String value) {
            addCriterion("http_interface_description like", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionNotLike(String value) {
            addCriterion("http_interface_description not like", value, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionIn(List<String> values) {
            addCriterion("http_interface_description in", values, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionNotIn(List<String> values) {
            addCriterion("http_interface_description not in", values, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionBetween(String value1, String value2) {
            addCriterion("http_interface_description between", value1, value2, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceDescriptionNotBetween(String value1, String value2) {
            addCriterion("http_interface_description not between", value1, value2, "httpInterfaceDescription");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackIsNull() {
            addCriterion("http_interface_need_async_callback is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackIsNotNull() {
            addCriterion("http_interface_need_async_callback is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackEqualTo(Boolean value) {
            addCriterion("http_interface_need_async_callback =", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackNotEqualTo(Boolean value) {
            addCriterion("http_interface_need_async_callback <>", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackGreaterThan(Boolean value) {
            addCriterion("http_interface_need_async_callback >", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackGreaterThanOrEqualTo(Boolean value) {
            addCriterion("http_interface_need_async_callback >=", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackLessThan(Boolean value) {
            addCriterion("http_interface_need_async_callback <", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackLessThanOrEqualTo(Boolean value) {
            addCriterion("http_interface_need_async_callback <=", value, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackIn(List<Boolean> values) {
            addCriterion("http_interface_need_async_callback in", values, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackNotIn(List<Boolean> values) {
            addCriterion("http_interface_need_async_callback not in", values, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackBetween(Boolean value1, Boolean value2) {
            addCriterion("http_interface_need_async_callback between", value1, value2, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceNeedAsyncCallbackNotBetween(Boolean value1, Boolean value2) {
            addCriterion("http_interface_need_async_callback not between", value1, value2, "httpInterfaceNeedAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeIsNull() {
            addCriterion("http_interface_config_mode is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeIsNotNull() {
            addCriterion("http_interface_config_mode is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeEqualTo(Integer value) {
            addCriterion("http_interface_config_mode =", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeNotEqualTo(Integer value) {
            addCriterion("http_interface_config_mode <>", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeGreaterThan(Integer value) {
            addCriterion("http_interface_config_mode >", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("http_interface_config_mode >=", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeLessThan(Integer value) {
            addCriterion("http_interface_config_mode <", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeLessThanOrEqualTo(Integer value) {
            addCriterion("http_interface_config_mode <=", value, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeIn(List<Integer> values) {
            addCriterion("http_interface_config_mode in", values, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeNotIn(List<Integer> values) {
            addCriterion("http_interface_config_mode not in", values, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_config_mode between", value1, value2, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceConfigModeNotBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_config_mode not between", value1, value2, "httpInterfaceConfigMode");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlIsNull() {
            addCriterion("http_interface_callback_request_url is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlIsNotNull() {
            addCriterion("http_interface_callback_request_url is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlEqualTo(String value) {
            addCriterion("http_interface_callback_request_url =", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlNotEqualTo(String value) {
            addCriterion("http_interface_callback_request_url <>", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlGreaterThan(String value) {
            addCriterion("http_interface_callback_request_url >", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_callback_request_url >=", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlLessThan(String value) {
            addCriterion("http_interface_callback_request_url <", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlLessThanOrEqualTo(String value) {
            addCriterion("http_interface_callback_request_url <=", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlLike(String value) {
            addCriterion("http_interface_callback_request_url like", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlNotLike(String value) {
            addCriterion("http_interface_callback_request_url not like", value, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlIn(List<String> values) {
            addCriterion("http_interface_callback_request_url in", values, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlNotIn(List<String> values) {
            addCriterion("http_interface_callback_request_url not in", values, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlBetween(String value1, String value2) {
            addCriterion("http_interface_callback_request_url between", value1, value2, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestUrlNotBetween(String value1, String value2) {
            addCriterion("http_interface_callback_request_url not between", value1, value2, "httpInterfaceCallbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodIsNull() {
            addCriterion("http_interface_callback_request_method is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodIsNotNull() {
            addCriterion("http_interface_callback_request_method is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodEqualTo(String value) {
            addCriterion("http_interface_callback_request_method =", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodNotEqualTo(String value) {
            addCriterion("http_interface_callback_request_method <>", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodGreaterThan(String value) {
            addCriterion("http_interface_callback_request_method >", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_callback_request_method >=", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodLessThan(String value) {
            addCriterion("http_interface_callback_request_method <", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("http_interface_callback_request_method <=", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodLike(String value) {
            addCriterion("http_interface_callback_request_method like", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodNotLike(String value) {
            addCriterion("http_interface_callback_request_method not like", value, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodIn(List<String> values) {
            addCriterion("http_interface_callback_request_method in", values, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodNotIn(List<String> values) {
            addCriterion("http_interface_callback_request_method not in", values, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodBetween(String value1, String value2) {
            addCriterion("http_interface_callback_request_method between", value1, value2, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceCallbackRequestMethodNotBetween(String value1, String value2) {
            addCriterion("http_interface_callback_request_method not between", value1, value2, "httpInterfaceCallbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayIsNull() {
            addCriterion("http_interface_sync_delay is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayIsNotNull() {
            addCriterion("http_interface_sync_delay is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayEqualTo(Integer value) {
            addCriterion("http_interface_sync_delay =", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayNotEqualTo(Integer value) {
            addCriterion("http_interface_sync_delay <>", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayGreaterThan(Integer value) {
            addCriterion("http_interface_sync_delay >", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("http_interface_sync_delay >=", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayLessThan(Integer value) {
            addCriterion("http_interface_sync_delay <", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayLessThanOrEqualTo(Integer value) {
            addCriterion("http_interface_sync_delay <=", value, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayIn(List<Integer> values) {
            addCriterion("http_interface_sync_delay in", values, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayNotIn(List<Integer> values) {
            addCriterion("http_interface_sync_delay not in", values, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_sync_delay between", value1, value2, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSyncDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_sync_delay not between", value1, value2, "httpInterfaceSyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayIsNull() {
            addCriterion("http_interface_async_delay is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayIsNotNull() {
            addCriterion("http_interface_async_delay is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayEqualTo(Integer value) {
            addCriterion("http_interface_async_delay =", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayNotEqualTo(Integer value) {
            addCriterion("http_interface_async_delay <>", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayGreaterThan(Integer value) {
            addCriterion("http_interface_async_delay >", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("http_interface_async_delay >=", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayLessThan(Integer value) {
            addCriterion("http_interface_async_delay <", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayLessThanOrEqualTo(Integer value) {
            addCriterion("http_interface_async_delay <=", value, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayIn(List<Integer> values) {
            addCriterion("http_interface_async_delay in", values, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayNotIn(List<Integer> values) {
            addCriterion("http_interface_async_delay not in", values, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_async_delay between", value1, value2, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceAsyncDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("http_interface_async_delay not between", value1, value2, "httpInterfaceAsyncDelay");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartIsNull() {
            addCriterion("http_interface_start is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartIsNotNull() {
            addCriterion("http_interface_start is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartEqualTo(Boolean value) {
            addCriterion("http_interface_start =", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartNotEqualTo(Boolean value) {
            addCriterion("http_interface_start <>", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartGreaterThan(Boolean value) {
            addCriterion("http_interface_start >", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartGreaterThanOrEqualTo(Boolean value) {
            addCriterion("http_interface_start >=", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartLessThan(Boolean value) {
            addCriterion("http_interface_start <", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartLessThanOrEqualTo(Boolean value) {
            addCriterion("http_interface_start <=", value, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartIn(List<Boolean> values) {
            addCriterion("http_interface_start in", values, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartNotIn(List<Boolean> values) {
            addCriterion("http_interface_start not in", values, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartBetween(Boolean value1, Boolean value2) {
            addCriterion("http_interface_start between", value1, value2, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceStartNotBetween(Boolean value1, Boolean value2) {
            addCriterion("http_interface_start not between", value1, value2, "httpInterfaceStart");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdIsNull() {
            addCriterion("http_interface_space_id is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdIsNotNull() {
            addCriterion("http_interface_space_id is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdEqualTo(Long value) {
            addCriterion("http_interface_space_id =", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdNotEqualTo(Long value) {
            addCriterion("http_interface_space_id <>", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdGreaterThan(Long value) {
            addCriterion("http_interface_space_id >", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("http_interface_space_id >=", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdLessThan(Long value) {
            addCriterion("http_interface_space_id <", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdLessThanOrEqualTo(Long value) {
            addCriterion("http_interface_space_id <=", value, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdIn(List<Long> values) {
            addCriterion("http_interface_space_id in", values, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdNotIn(List<Long> values) {
            addCriterion("http_interface_space_id not in", values, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdBetween(Long value1, Long value2) {
            addCriterion("http_interface_space_id between", value1, value2, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceSpaceIdNotBetween(Long value1, Long value2) {
            addCriterion("http_interface_space_id not between", value1, value2, "httpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserIsNull() {
            addCriterion("http_interface_last_update_user is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserIsNotNull() {
            addCriterion("http_interface_last_update_user is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserEqualTo(String value) {
            addCriterion("http_interface_last_update_user =", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserNotEqualTo(String value) {
            addCriterion("http_interface_last_update_user <>", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserGreaterThan(String value) {
            addCriterion("http_interface_last_update_user >", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("http_interface_last_update_user >=", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserLessThan(String value) {
            addCriterion("http_interface_last_update_user <", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("http_interface_last_update_user <=", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserLike(String value) {
            addCriterion("http_interface_last_update_user like", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserNotLike(String value) {
            addCriterion("http_interface_last_update_user not like", value, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserIn(List<String> values) {
            addCriterion("http_interface_last_update_user in", values, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserNotIn(List<String> values) {
            addCriterion("http_interface_last_update_user not in", values, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserBetween(String value1, String value2) {
            addCriterion("http_interface_last_update_user between", value1, value2, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateUserNotBetween(String value1, String value2) {
            addCriterion("http_interface_last_update_user not between", value1, value2, "httpInterfaceLastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeIsNull() {
            addCriterion("http_interface_last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeIsNotNull() {
            addCriterion("http_interface_last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeEqualTo(Date value) {
            addCriterion("http_interface_last_update_time =", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("http_interface_last_update_time <>", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeGreaterThan(Date value) {
            addCriterion("http_interface_last_update_time >", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("http_interface_last_update_time >=", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeLessThan(Date value) {
            addCriterion("http_interface_last_update_time <", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("http_interface_last_update_time <=", value, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeIn(List<Date> values) {
            addCriterion("http_interface_last_update_time in", values, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("http_interface_last_update_time not in", values, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("http_interface_last_update_time between", value1, value2, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andHttpInterfaceLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("http_interface_last_update_time not between", value1, value2, "httpInterfaceLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNull() {
            addCriterion("op_type is null");
            return (Criteria) this;
        }

        public Criteria andOpTypeIsNotNull() {
            addCriterion("op_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpTypeEqualTo(Integer value) {
            addCriterion("op_type =", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotEqualTo(Integer value) {
            addCriterion("op_type <>", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThan(Integer value) {
            addCriterion("op_type >", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_type >=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThan(Integer value) {
            addCriterion("op_type <", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThanOrEqualTo(Integer value) {
            addCriterion("op_type <=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeIn(List<Integer> values) {
            addCriterion("op_type in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotIn(List<Integer> values) {
            addCriterion("op_type not in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeBetween(Integer value1, Integer value2) {
            addCriterion("op_type between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("op_type not between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNull() {
            addCriterion("op_user is null");
            return (Criteria) this;
        }

        public Criteria andOpUserIsNotNull() {
            addCriterion("op_user is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserEqualTo(String value) {
            addCriterion("op_user =", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotEqualTo(String value) {
            addCriterion("op_user <>", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThan(String value) {
            addCriterion("op_user >", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserGreaterThanOrEqualTo(String value) {
            addCriterion("op_user >=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThan(String value) {
            addCriterion("op_user <", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLessThanOrEqualTo(String value) {
            addCriterion("op_user <=", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserLike(String value) {
            addCriterion("op_user like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotLike(String value) {
            addCriterion("op_user not like", value, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserIn(List<String> values) {
            addCriterion("op_user in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotIn(List<String> values) {
            addCriterion("op_user not in", values, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserBetween(String value1, String value2) {
            addCriterion("op_user between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andOpUserNotBetween(String value1, String value2) {
            addCriterion("op_user not between", value1, value2, "opUser");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeIsNull() {
            addCriterion("snapshot_time is null");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeIsNotNull() {
            addCriterion("snapshot_time is not null");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeEqualTo(Date value) {
            addCriterion("snapshot_time =", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeNotEqualTo(Date value) {
            addCriterion("snapshot_time <>", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeGreaterThan(Date value) {
            addCriterion("snapshot_time >", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("snapshot_time >=", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeLessThan(Date value) {
            addCriterion("snapshot_time <", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeLessThanOrEqualTo(Date value) {
            addCriterion("snapshot_time <=", value, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeIn(List<Date> values) {
            addCriterion("snapshot_time in", values, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeNotIn(List<Date> values) {
            addCriterion("snapshot_time not in", values, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeBetween(Date value1, Date value2) {
            addCriterion("snapshot_time between", value1, value2, "snapshotTime");
            return (Criteria) this;
        }

        public Criteria andSnapshotTimeNotBetween(Date value1, Date value2) {
            addCriterion("snapshot_time not between", value1, value2, "snapshotTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}