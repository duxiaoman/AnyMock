package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.entity.MockContext;
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
    public String exec(MockContext mockContext, String text) {
        if (mockContext.getGroovyBinding() == null) {
            Binding binding = new Binding();
            binding.setProperty("request", mockContext.getHttpServletRequest());
            binding.setProperty("response", mockContext.getHttpServletResponse());
            mockContext.setGroovyBinding(binding);
        }

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        mockContext.getGroovyBinding().setProperty("out", printWriter);

        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");
        Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource);
        Script script = InvokerHelper.createScript(groovyClass, mockContext.getGroovyBinding());
        script.run();
        return stringWriter.toString();
    }
}
