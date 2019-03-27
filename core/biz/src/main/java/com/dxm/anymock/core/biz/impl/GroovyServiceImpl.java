package com.dxm.anymock.core.biz.impl;

import com.dxm.anymock.core.biz.GroovyService;
import com.dxm.anymock.core.biz.entity.HttpMockContext;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.Script;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class GroovyServiceImpl implements GroovyService {

    private static final Logger logger = LoggerFactory.getLogger(GroovyServiceImpl.class);

    private static final String PRE_IMPORT = ""
            + "import groovy.security.XmlSlurper\n"
            + "import groovy.json.JsonSlurper\n";

    @Autowired
    private GroovyClassLoader groovyClassLoader;

    @Override
    public String exec(HttpMockContext httpMockContext, String text) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        httpMockContext.getGroovyBinding().setProperty("out", printWriter);

        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";
        text = PRE_IMPORT + text;
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");

        try {
            Class groovyClass = groovyClassLoader.parseClass(groovyCodeSource);
            Script script = InvokerHelper.createScript(groovyClass, httpMockContext.getGroovyBinding());
            Object retVal = script.run();
            logger.info(stringWriter.toString());
            if (retVal.getClass().getMethod("toString").getDeclaringClass().equals(Object.class)) {
                throw new RuntimeException("Failed to cast script exec result to String");
            }
            return retVal.toString();
        } catch (Throwable e) {
            throw new GroovyScriptExecException(httpMockContext.getRawHttpRequestMsg(), e);
        }
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
