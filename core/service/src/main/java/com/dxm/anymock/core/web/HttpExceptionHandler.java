package com.dxm.anymock.core.web;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.common.base.ResultCodeTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HttpExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpExceptionHandler.class);

    private static final String HORIZONTAL_RULE
            = "\n##############################################################################\n";

    @Autowired
    private ResultCodeTranslator resultCodeTranslator;

    private String buildRespTitle(ResultCode resultCode) {
        return String.format("[%s-%s]", resultCode.getCode(), resultCodeTranslator.translate(resultCode));
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseEntity<String> handleBaseException(BizException e) {
        logger.warn("", e);
        return new ResponseEntity<>(buildRespTitle(e.getResultCode()), HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler(GroovyScriptExecException.class)
    @ResponseBody
    public ResponseEntity<String> handleGroovyScriptExecException(GroovyScriptExecException e) {
        logger.warn("", e);
        String body = buildRespTitle(ResultCode.GROOVY_SCRIPT_EXEC_EXCEPTION)
                + HORIZONTAL_RULE
                + e.getMessage()
                + HORIZONTAL_RULE
                + e.getRawHttpRequestMsg();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("", e);
        return new ResponseEntity<>(buildRespTitle(ResultCode.UNEXPECTED_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

