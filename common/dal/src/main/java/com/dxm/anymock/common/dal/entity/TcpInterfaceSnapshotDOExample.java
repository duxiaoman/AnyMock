package com.dxm.anymock.common.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TcpInterfaceSnapshotDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TcpInterfaceSnapshotDOExample() {
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

        public Criteria andTcpInterfaceIdIsNull() {
            addCriterion("tcp_interface_id is null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdIsNotNull() {
            addCriterion("tcp_interface_id is not null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdEqualTo(Long value) {
            addCriterion("tcp_interface_id =", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdNotEqualTo(Long value) {
            addCriterion("tcp_interface_id <>", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdGreaterThan(Long value) {
            addCriterion("tcp_interface_id >", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tcp_interface_id >=", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdLessThan(Long value) {
            addCriterion("tcp_interface_id <", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdLessThanOrEqualTo(Long value) {
            addCriterion("tcp_interface_id <=", value, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdIn(List<Long> values) {
            addCriterion("tcp_interface_id in", values, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdNotIn(List<Long> values) {
            addCriterion("tcp_interface_id not in", values, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdBetween(Long value1, Long value2) {
            addCriterion("tcp_interface_id between", value1, value2, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIdNotBetween(Long value1, Long value2) {
            addCriterion("tcp_interface_id not between", value1, value2, "tcpInterfaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriIsNull() {
            addCriterion("tcp_interface_request_uri is null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriIsNotNull() {
            addCriterion("tcp_interface_request_uri is not null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriEqualTo(String value) {
            addCriterion("tcp_interface_request_uri =", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriNotEqualTo(String value) {
            addCriterion("tcp_interface_request_uri <>", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriGreaterThan(String value) {
            addCriterion("tcp_interface_request_uri >", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriGreaterThanOrEqualTo(String value) {
            addCriterion("tcp_interface_request_uri >=", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriLessThan(String value) {
            addCriterion("tcp_interface_request_uri <", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriLessThanOrEqualTo(String value) {
            addCriterion("tcp_interface_request_uri <=", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriLike(String value) {
            addCriterion("tcp_interface_request_uri like", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriNotLike(String value) {
            addCriterion("tcp_interface_request_uri not like", value, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriIn(List<String> values) {
            addCriterion("tcp_interface_request_uri in", values, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriNotIn(List<String> values) {
            addCriterion("tcp_interface_request_uri not in", values, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriBetween(String value1, String value2) {
            addCriterion("tcp_interface_request_uri between", value1, value2, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceRequestUriNotBetween(String value1, String value2) {
            addCriterion("tcp_interface_request_uri not between", value1, value2, "tcpInterfaceRequestUri");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdIsNull() {
            addCriterion("tcp_interface_space_id is null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdIsNotNull() {
            addCriterion("tcp_interface_space_id is not null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdEqualTo(Long value) {
            addCriterion("tcp_interface_space_id =", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdNotEqualTo(Long value) {
            addCriterion("tcp_interface_space_id <>", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdGreaterThan(Long value) {
            addCriterion("tcp_interface_space_id >", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tcp_interface_space_id >=", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdLessThan(Long value) {
            addCriterion("tcp_interface_space_id <", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdLessThanOrEqualTo(Long value) {
            addCriterion("tcp_interface_space_id <=", value, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdIn(List<Long> values) {
            addCriterion("tcp_interface_space_id in", values, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdNotIn(List<Long> values) {
            addCriterion("tcp_interface_space_id not in", values, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdBetween(Long value1, Long value2) {
            addCriterion("tcp_interface_space_id between", value1, value2, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceSpaceIdNotBetween(Long value1, Long value2) {
            addCriterion("tcp_interface_space_id not between", value1, value2, "tcpInterfaceSpaceId");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIsNull() {
            addCriterion("tcp_interface is null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIsNotNull() {
            addCriterion("tcp_interface is not null");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceEqualTo(String value) {
            addCriterion("tcp_interface =", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceNotEqualTo(String value) {
            addCriterion("tcp_interface <>", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceGreaterThan(String value) {
            addCriterion("tcp_interface >", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceGreaterThanOrEqualTo(String value) {
            addCriterion("tcp_interface >=", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceLessThan(String value) {
            addCriterion("tcp_interface <", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceLessThanOrEqualTo(String value) {
            addCriterion("tcp_interface <=", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceLike(String value) {
            addCriterion("tcp_interface like", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceNotLike(String value) {
            addCriterion("tcp_interface not like", value, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceIn(List<String> values) {
            addCriterion("tcp_interface in", values, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceNotIn(List<String> values) {
            addCriterion("tcp_interface not in", values, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceBetween(String value1, String value2) {
            addCriterion("tcp_interface between", value1, value2, "tcpInterface");
            return (Criteria) this;
        }

        public Criteria andTcpInterfaceNotBetween(String value1, String value2) {
            addCriterion("tcp_interface not between", value1, value2, "tcpInterface");
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

        public Criteria andOpTypeEqualTo(String value) {
            addCriterion("op_type =", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotEqualTo(String value) {
            addCriterion("op_type <>", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThan(String value) {
            addCriterion("op_type >", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("op_type >=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThan(String value) {
            addCriterion("op_type <", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLessThanOrEqualTo(String value) {
            addCriterion("op_type <=", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeLike(String value) {
            addCriterion("op_type like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotLike(String value) {
            addCriterion("op_type not like", value, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeIn(List<String> values) {
            addCriterion("op_type in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotIn(List<String> values) {
            addCriterion("op_type not in", values, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeBetween(String value1, String value2) {
            addCriterion("op_type between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andOpTypeNotBetween(String value1, String value2) {
            addCriterion("op_type not between", value1, value2, "opType");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
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