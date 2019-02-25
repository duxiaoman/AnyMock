package com.dxm.anymock.core.biz.impl;

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
        ProfilerRegistry profilerRegistry = ProfilerRegistry.getThreadContextInstance();
        Profiler profiler = profilerRegistry.get(GroovyService.class.getSimpleName());

        profiler.start("bind");
        if (mockContext.getGroovyBinding() == null) {
            Binding binding = new Binding();
            binding.setProperty("request", mockContext.getHttpServletRequest());
            binding.setProperty("response", mockContext.getHttpServletResponse());
            mockContext.setGroovyBinding(binding);
        }

        profiler.start("redirect output");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        mockContext.getGroovyBinding().setProperty("out", printWriter);

        profiler.start("gen script name");
        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";

        profiler.start("groovy code source");
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");

        profiler.start("parse class");
        Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource);

        profiler.start("create script");
        Script script = InvokerHelper.createScript(groovyClass, mockContext.getGroovyBinding());

        profiler.start("run");
        script.run();
        return stringWriter.toString();
    }
}
