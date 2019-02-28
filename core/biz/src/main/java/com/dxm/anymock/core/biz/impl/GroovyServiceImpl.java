package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.exception.GroovyScriptExecException;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.entity.HttpMockContext;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class GroovyServiceImpl implements GroovyService {

    @Autowired
    private GroovyClassLoader groovyClassLoader;

    @Override
    public String exec(HttpMockContext httpMockContext, String text) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        httpMockContext.getGroovyBinding().setProperty("out", printWriter);

        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");

        try {
            Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource);
            Script script = InvokerHelper.createScript(groovyClass, httpMockContext.getGroovyBinding());
            script.run();
        } catch (Throwable e) {
            throw new GroovyScriptExecException(httpMockContext.getRawHttpRequestMsg(), e);
        }
        return stringWriter.toString();
    }

    @Override
    public void initBinding(HttpMockContext httpMockContext) {
        httpMockContext.setGroovyBinding(new Binding());
    }

    @Override
    public void bindSyncProperty(HttpMockContext httpMockContext) {
        Binding binding = httpMockContext.getGroovyBinding();
        binding.setProperty("request", httpMockContext.getHttpServletRequest());
        binding.setProperty("response", httpMockContext.getHttpServletResponse());
    }

    @Override
    public void bindAsyncProperty(HttpMockContext httpMockContext) {
        Binding binding = httpMockContext.getGroovyBinding();
        binding.setProperty("httpURLConnection", httpMockContext.getHttpURLConnection());
    }
}
