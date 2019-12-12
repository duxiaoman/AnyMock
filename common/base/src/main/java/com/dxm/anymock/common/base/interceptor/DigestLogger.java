package com.dxm.anymock.common.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class DigestLogger implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(DigestLogger.class);

    private boolean printArguments = true;

    private boolean printResults   = true;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        Object retValue = null;
        String result = "N";
        long beginTime = System.currentTimeMillis();

        try {
            retValue = invocation.proceed();
            result = "Y";
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("[({}.{},{},{}ms)({})({})]", clazz.getSimpleName(), method.getName(),
                    result, endTime - beginTime, getArgumentsString(invocation),
                    getResultsString(retValue));
        }

        return retValue;
    }

    private String getArgumentsString(MethodInvocation invocation) {
        String arguments = "-";
        if (printArguments) {
            arguments = StringUtils.join(JSONObject.toJSONString(invocation.getArguments()), ",");
        }
        return arguments;
    }

    private String getResultsString(Object retValue) {
        if (printResults) {
            return retValue == null ? "null" : JSONObject.toJSONString(retValue);
        } else {
            return "-";
        }
    }

    public boolean isPrintArguments() {
        return printArguments;
    }

    public void setPrintArguments(boolean printArguments) {
        this.printArguments = printArguments;
    }

    public boolean isPrintResults() {
        return printResults;
    }

    public void setPrintResults(boolean printResults) {
        this.printResults = printResults;
    }
}
