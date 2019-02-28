package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.common.base.exception.GroovyScriptExecException;
import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.entity.MockContext;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.profiler.Profiler;
import org.slf4j.profiler.ProfilerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class GroovyServiceImpl implements GroovyService {

    @Autowired
    private GroovyClassLoader groovyClassLoader;

    @Override
    public String exec(MockContext mockContext, String text) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        mockContext.getGroovyBinding().setProperty("out", printWriter);

        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");

        try {
            Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource);
            Script script = InvokerHelper.createScript(groovyClass, mockContext.getGroovyBinding());
            script.run();
        } catch (Throwable e) {
            throw new GroovyScriptExecException(mockContext.getRawHttpRequestMsg(), e);
        }
        return stringWriter.toString();
    }

    @Override
    public void initBinding(MockContext mockContext) {
        mockContext.setGroovyBinding(new Binding());
    }

    @Override
    public void bindSyncProperty(MockContext mockContext) {
        Binding binding = mockContext.getGroovyBinding();
        binding.setProperty("request", mockContext.getHttpServletRequest());
        binding.setProperty("response", mockContext.getHttpServletResponse());
    }

    @Override
    public void bindAsyncProperty(MockContext mockContext) {
        Binding binding = mockContext.getGroovyBinding();
        binding.setProperty("httpURLConnection", mockContext.getHttpURLConnection());
    }
}
