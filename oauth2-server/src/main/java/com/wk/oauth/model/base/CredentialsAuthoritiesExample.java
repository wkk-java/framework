package com.wk.oauth.model.base;

import java.util.ArrayList;
import java.util.List;

public class CredentialsAuthoritiesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CredentialsAuthoritiesExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andCredentialsIdIsNull() {
            addCriterion("credentials_id is null");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdIsNotNull() {
            addCriterion("credentials_id is not null");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdEqualTo(Long value) {
            addCriterion("credentials_id =", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdNotEqualTo(Long value) {
            addCriterion("credentials_id <>", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdGreaterThan(Long value) {
            addCriterion("credentials_id >", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("credentials_id >=", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdLessThan(Long value) {
            addCriterion("credentials_id <", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdLessThanOrEqualTo(Long value) {
            addCriterion("credentials_id <=", value, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdIn(List<Long> values) {
            addCriterion("credentials_id in", values, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdNotIn(List<Long> values) {
            addCriterion("credentials_id not in", values, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdBetween(Long value1, Long value2) {
            addCriterion("credentials_id between", value1, value2, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andCredentialsIdNotBetween(Long value1, Long value2) {
            addCriterion("credentials_id not between", value1, value2, "credentialsId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdIsNull() {
            addCriterion("authorities_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdIsNotNull() {
            addCriterion("authorities_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdEqualTo(Long value) {
            addCriterion("authorities_id =", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdNotEqualTo(Long value) {
            addCriterion("authorities_id <>", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdGreaterThan(Long value) {
            addCriterion("authorities_id >", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("authorities_id >=", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdLessThan(Long value) {
            addCriterion("authorities_id <", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdLessThanOrEqualTo(Long value) {
            addCriterion("authorities_id <=", value, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdIn(List<Long> values) {
            addCriterion("authorities_id in", values, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdNotIn(List<Long> values) {
            addCriterion("authorities_id not in", values, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdBetween(Long value1, Long value2) {
            addCriterion("authorities_id between", value1, value2, "authoritiesId");
            return (Criteria) this;
        }

        public Criteria andAuthoritiesIdNotBetween(Long value1, Long value2) {
            addCriterion("authorities_id not between", value1, value2, "authoritiesId");
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