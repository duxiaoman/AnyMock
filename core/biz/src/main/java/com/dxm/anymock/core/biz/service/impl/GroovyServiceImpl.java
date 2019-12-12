package com.dxm.anymock.core.biz.service.impl;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.core.biz.service.GroovyService;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.Script;
import org.apache.commons.lang3.StringUtils;
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

    private static final String PRE_IMPORT = "import groovy.util.XmlSlurper;\n"
            + "import groovy.json.JsonSlurper;\n"
            + "import io.netty.channel.*;\n"
            + "import io.netty.buffer.ByteBuf;\n"
            + "import io.netty.buffer.Unpooled;\n"
            + "import io.netty.handler.codec.string.StringDecoder;\n"
            + "import io.netty.handler.codec.string.StringEncoder;\n"
            + "import io.netty.util.CharsetUtil;\n"
            + "import com.dxm.anymock.core.biz.codec.protobuf.DemoReqProto;\n"
            + "import com.dxm.anymock.core.biz.codec.protobuf.DemoRespProto;\n"
            + "import io.netty.handler.codec.protobuf.ProtobufDecoder;\n"
            + "import io.netty.handler.codec.protobuf.ProtobufEncoder;\n";

    @Autowired
    private GroovyClassLoader groovyClassLoader;

    @Override
    public Object exec(Binding binding, String text) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        binding.setProperty("out", printWriter);

        String name = "script" + System.currentTimeMillis() + Math.abs(text.hashCode()) + ".groovy";
        text = PRE_IMPORT + text;
        GroovyCodeSource groovyCodeSource = new GroovyCodeSource(text, name, "/groovy/script");

        Class groovyClass;
        try {
            groovyClass = groovyClassLoader.parseClass(groovyCodeSource);
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new BizException(ResultCode.GROOVY_COMPILE_EXCEPTION, e);
        }

        Script script = InvokerHelper.createScript(groovyClass, binding);

        Object result;
        try {
            result = script.run();
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new BizException(ResultCode.GROOVY_RUNTIME_EXCEPTION, e);
        }

        String stdout = stringWriter.toString();
        if (StringUtils.isNotBlank(stdout)) {
            logger.info("stdout = {}", stdout);
        }

        if (result == null) {
            return "";
        }

        return result;
    }
}
