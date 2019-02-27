package com.dxm.anymock.core.web;

import com.dxm.anymock.common.base.BaseResponse;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.base.exception.GroovyScriptExecException;
import com.dxm.anymock.common.base.util.MessageUtil;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.ant.Groovy;
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

    @Autowired
    private MessageUtil messageUtil;

    private String buildRespTitle(ErrorCode errorCode) {
        return String.format("[%s-%s]", errorCode.getCode(), messageUtil.getMsg(errorCode));
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<String> handleBaseException(BaseException e) {
        logger.warn("", e);
        return new ResponseEntity<>(buildRespTitle(e.getErrorCode()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroovyScriptExecException.class)
    @ResponseBody
    public ResponseEntity<String> handleGroovyScriptExecException(GroovyScriptExecException e) {
        logger.warn("", e);
        String body = buildRespTitle(ErrorCode.GROOVY_SCRIPT_EXEC_EXCEPTION) + "\n" + e.getMessage();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("", e);
        return new ResponseEntity<>(buildRespTitle(ErrorCode.UNEXPECTED_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

