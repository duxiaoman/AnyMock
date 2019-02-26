package com.dxm.anymock.common.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpInterfacePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HttpInterfacePOExample() {
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

        public Criteria andRequestUriIsNull() {
            addCriterion("request_uri is null");
            return (Criteria) this;
        }

        public Criteria andRequestUriIsNotNull() {
            addCriterion("request_uri is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUriEqualTo(String value) {
            addCriterion("request_uri =", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriNotEqualTo(String value) {
            addCriterion("request_uri <>", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriGreaterThan(String value) {
            addCriterion("request_uri >", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriGreaterThanOrEqualTo(String value) {
            addCriterion("request_uri >=", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriLessThan(String value) {
            addCriterion("request_uri <", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriLessThanOrEqualTo(String value) {
            addCriterion("request_uri <=", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriLike(String value) {
            addCriterion("request_uri like", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriNotLike(String value) {
            addCriterion("request_uri not like", value, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriIn(List<String> values) {
            addCriterion("request_uri in", values, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriNotIn(List<String> values) {
            addCriterion("request_uri not in", values, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriBetween(String value1, String value2) {
            addCriterion("request_uri between", value1, value2, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestUriNotBetween(String value1, String value2) {
            addCriterion("request_uri not between", value1, value2, "requestUri");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIsNull() {
            addCriterion("request_method is null");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIsNotNull() {
            addCriterion("request_method is not null");
            return (Criteria) this;
        }

        public Criteria andRequestMethodEqualTo(String value) {
            addCriterion("request_method =", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotEqualTo(String value) {
            addCriterion("request_method <>", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodGreaterThan(String value) {
            addCriterion("request_method >", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("request_method >=", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLessThan(String value) {
            addCriterion("request_method <", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("request_method <=", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodLike(String value) {
            addCriterion("request_method like", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotLike(String value) {
            addCriterion("request_method not like", value, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodIn(List<String> values) {
            addCriterion("request_method in", values, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotIn(List<String> values) {
            addCriterion("request_method not in", values, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodBetween(String value1, String value2) {
            addCriterion("request_method between", value1, value2, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andRequestMethodNotBetween(String value1, String value2) {
            addCriterion("request_method not between", value1, value2, "requestMethod");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackIsNull() {
            addCriterion("need_async_callback is null");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackIsNotNull() {
            addCriterion("need_async_callback is not null");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackEqualTo(Boolean value) {
            addCriterion("need_async_callback =", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackNotEqualTo(Boolean value) {
            addCriterion("need_async_callback <>", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackGreaterThan(Boolean value) {
            addCriterion("need_async_callback >", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackGreaterThanOrEqualTo(Boolean value) {
            addCriterion("need_async_callback >=", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackLessThan(Boolean value) {
            addCriterion("need_async_callback <", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackLessThanOrEqualTo(Boolean value) {
            addCriterion("need_async_callback <=", value, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackIn(List<Boolean> values) {
            addCriterion("need_async_callback in", values, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackNotIn(List<Boolean> values) {
            addCriterion("need_async_callback not in", values, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackBetween(Boolean value1, Boolean value2) {
            addCriterion("need_async_callback between", value1, value2, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andNeedAsyncCallbackNotBetween(Boolean value1, Boolean value2) {
            addCriterion("need_async_callback not between", value1, value2, "needAsyncCallback");
            return (Criteria) this;
        }

        public Criteria andConfigModeIsNull() {
            addCriterion("config_mode is null");
            return (Criteria) this;
        }

        public Criteria andConfigModeIsNotNull() {
            addCriterion("config_mode is not null");
            return (Criteria) this;
        }

        public Criteria andConfigModeEqualTo(Integer value) {
            addCriterion("config_mode =", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeNotEqualTo(Integer value) {
            addCriterion("config_mode <>", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeGreaterThan(Integer value) {
            addCriterion("config_mode >", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("config_mode >=", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeLessThan(Integer value) {
            addCriterion("config_mode <", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeLessThanOrEqualTo(Integer value) {
            addCriterion("config_mode <=", value, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeIn(List<Integer> values) {
            addCriterion("config_mode in", values, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeNotIn(List<Integer> values) {
            addCriterion("config_mode not in", values, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeBetween(Integer value1, Integer value2) {
            addCriterion("config_mode between", value1, value2, "configMode");
            return (Criteria) this;
        }

        public Criteria andConfigModeNotBetween(Integer value1, Integer value2) {
            addCriterion("config_mode not between", value1, value2, "configMode");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlIsNull() {
            addCriterion("callback_request_url is null");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlIsNotNull() {
            addCriterion("callback_request_url is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlEqualTo(String value) {
            addCriterion("callback_request_url =", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlNotEqualTo(String value) {
            addCriterion("callback_request_url <>", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlGreaterThan(String value) {
            addCriterion("callback_request_url >", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlGreaterThanOrEqualTo(String value) {
            addCriterion("callback_request_url >=", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlLessThan(String value) {
            addCriterion("callback_request_url <", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlLessThanOrEqualTo(String value) {
            addCriterion("callback_request_url <=", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlLike(String value) {
            addCriterion("callback_request_url like", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlNotLike(String value) {
            addCriterion("callback_request_url not like", value, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlIn(List<String> values) {
            addCriterion("callback_request_url in", values, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlNotIn(List<String> values) {
            addCriterion("callback_request_url not in", values, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlBetween(String value1, String value2) {
            addCriterion("callback_request_url between", value1, value2, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestUrlNotBetween(String value1, String value2) {
            addCriterion("callback_request_url not between", value1, value2, "callbackRequestUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodIsNull() {
            addCriterion("callback_request_method is null");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodIsNotNull() {
            addCriterion("callback_request_method is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodEqualTo(String value) {
            addCriterion("callback_request_method =", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodNotEqualTo(String value) {
            addCriterion("callback_request_method <>", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodGreaterThan(String value) {
            addCriterion("callback_request_method >", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodGreaterThanOrEqualTo(String value) {
            addCriterion("callback_request_method >=", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodLessThan(String value) {
            addCriterion("callback_request_method <", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodLessThanOrEqualTo(String value) {
            addCriterion("callback_request_method <=", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodLike(String value) {
            addCriterion("callback_request_method like", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodNotLike(String value) {
            addCriterion("callback_request_method not like", value, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodIn(List<String> values) {
            addCriterion("callback_request_method in", values, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodNotIn(List<String> values) {
            addCriterion("callback_request_method not in", values, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodBetween(String value1, String value2) {
            addCriterion("callback_request_method between", value1, value2, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andCallbackRequestMethodNotBetween(String value1, String value2) {
            addCriterion("callback_request_method not between", value1, value2, "callbackRequestMethod");
            return (Criteria) this;
        }

        public Criteria andSyncDelayIsNull() {
            addCriterion("sync_delay is null");
            return (Criteria) this;
        }

        public Criteria andSyncDelayIsNotNull() {
            addCriterion("sync_delay is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDelayEqualTo(Integer value) {
            addCriterion("sync_delay =", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayNotEqualTo(Integer value) {
            addCriterion("sync_delay <>", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayGreaterThan(Integer value) {
            addCriterion("sync_delay >", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("sync_delay >=", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayLessThan(Integer value) {
            addCriterion("sync_delay <", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayLessThanOrEqualTo(Integer value) {
            addCriterion("sync_delay <=", value, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayIn(List<Integer> values) {
            addCriterion("sync_delay in", values, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayNotIn(List<Integer> values) {
            addCriterion("sync_delay not in", values, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayBetween(Integer value1, Integer value2) {
            addCriterion("sync_delay between", value1, value2, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andSyncDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("sync_delay not between", value1, value2, "syncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayIsNull() {
            addCriterion("async_delay is null");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayIsNotNull() {
            addCriterion("async_delay is not null");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayEqualTo(Integer value) {
            addCriterion("async_delay =", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayNotEqualTo(Integer value) {
            addCriterion("async_delay <>", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayGreaterThan(Integer value) {
            addCriterion("async_delay >", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("async_delay >=", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayLessThan(Integer value) {
            addCriterion("async_delay <", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayLessThanOrEqualTo(Integer value) {
            addCriterion("async_delay <=", value, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayIn(List<Integer> values) {
            addCriterion("async_delay in", values, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayNotIn(List<Integer> values) {
            addCriterion("async_delay not in", values, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayBetween(Integer value1, Integer value2) {
            addCriterion("async_delay between", value1, value2, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andAsyncDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("async_delay not between", value1, value2, "asyncDelay");
            return (Criteria) this;
        }

        public Criteria andStartIsNull() {
            addCriterion("start is null");
            return (Criteria) this;
        }

        public Criteria andStartIsNotNull() {
            addCriterion("start is not null");
            return (Criteria) this;
        }

        public Criteria andStartEqualTo(Boolean value) {
            addCriterion("start =", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotEqualTo(Boolean value) {
            addCriterion("start <>", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThan(Boolean value) {
            addCriterion("start >", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartGreaterThanOrEqualTo(Boolean value) {
            addCriterion("start >=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThan(Boolean value) {
            addCriterion("start <", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartLessThanOrEqualTo(Boolean value) {
            addCriterion("start <=", value, "start");
            return (Criteria) this;
        }

        public Criteria andStartIn(List<Boolean> values) {
            addCriterion("start in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotIn(List<Boolean> values) {
            addCriterion("start not in", values, "start");
            return (Criteria) this;
        }

        public Criteria andStartBetween(Boolean value1, Boolean value2) {
            addCriterion("start between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andStartNotBetween(Boolean value1, Boolean value2) {
            addCriterion("start not between", value1, value2, "start");
            return (Criteria) this;
        }

        public Criteria andSpaceIdIsNull() {
            addCriterion("space_id is null");
            return (Criteria) this;
        }

        public Criteria andSpaceIdIsNotNull() {
            addCriterion("space_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpaceIdEqualTo(Long value) {
            addCriterion("space_id =", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotEqualTo(Long value) {
            addCriterion("space_id <>", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdGreaterThan(Long value) {
            addCriterion("space_id >", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("space_id >=", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdLessThan(Long value) {
            addCriterion("space_id <", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdLessThanOrEqualTo(Long value) {
            addCriterion("space_id <=", value, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdIn(List<Long> values) {
            addCriterion("space_id in", values, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotIn(List<Long> values) {
            addCriterion("space_id not in", values, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdBetween(Long value1, Long value2) {
            addCriterion("space_id between", value1, value2, "spaceId");
            return (Criteria) this;
        }

        public Criteria andSpaceIdNotBetween(Long value1, Long value2) {
            addCriterion("space_id not between", value1, value2, "spaceId");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIsNull() {
            addCriterion("last_update_user is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIsNotNull() {
            addCriterion("last_update_user is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserEqualTo(String value) {
            addCriterion("last_update_user =", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNotEqualTo(String value) {
            addCriterion("last_update_user <>", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserGreaterThan(String value) {
            addCriterion("last_update_user >", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("last_update_user >=", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserLessThan(String value) {
            addCriterion("last_update_user <", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("last_update_user <=", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserLike(String value) {
            addCriterion("last_update_user like", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNotLike(String value) {
            addCriterion("last_update_user not like", value, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserIn(List<String> values) {
            addCriterion("last_update_user in", values, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNotIn(List<String> values) {
            addCriterion("last_update_user not in", values, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserBetween(String value1, String value2) {
            addCriterion("last_update_user between", value1, value2, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateUserNotBetween(String value1, String value2) {
            addCriterion("last_update_user not between", value1, value2, "lastUpdateUser");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
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