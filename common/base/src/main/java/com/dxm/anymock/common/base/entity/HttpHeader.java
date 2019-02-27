package com.dxm.anymock.common.base.entity;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.check.CommonInsertCheck;
import com.dxm.anymock.common.base.check.CommonUpdateCheck;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeader implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(HttpHeader.class);

    @NotBlank(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String name;

    @NotNull(groups = {CommonInsertCheck.class, CommonUpdateCheck.class})
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HttpHeader)) {
            return false;
        }
        HttpHeader httpHeader = (HttpHeader)o;
        return new EqualsBuilder()
                .append(name, httpHeader.name)
                .append(value, httpHeader.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(value)
                .toHashCode();
    }

    private static Map<String, HttpHeader> list2Map(List<HttpHeader> httpHeaderList) {
        Map<String, HttpHeader> map = new HashMap<>();
        httpHeaderList.forEach(httpHeader -> map.put(httpHeader.getName(), httpHeader));
        return map;
    }

    public static boolean equals(List<HttpHeader> left, List<HttpHeader> right) {
        return list2Map(left).equals(list2Map(right));
    }
}
