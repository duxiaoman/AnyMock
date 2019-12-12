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

import java.util.Map;

@ControllerAdvice
public class HttpExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpExceptionHandler.class);

    @Autowired
    private ResultCodeTranslator translator;

    private String buildRespTitle(ResultCode resultCode) {
        Map<String, String> resultMap = translator.translate(resultCode);
        String resultMsg = resultMap.get("resultMsg");
        return String.format("[%s-%s]", resultCode.getCode(), resultMsg);
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseEntity<String> handleBizException(BizException e) {
        ResultCode resultCode = e.getResultCode();
        logger.warn("", e);
        if (e.getResultCode() == ResultCode.GROOVY_COMPILE_EXCEPTION || e.getResultCode() == ResultCode.GROOVY_RUNTIME_EXCEPTION) {
            String body = buildRespTitle(resultCode) + "\n"
                       + "###############################################################################\n"
                       + e.getCause().getMessage() + "\n"
                       + "##############################################################################\n";
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(buildRespTitle(resultCode), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("", e);
        return new ResponseEntity<>(buildRespTitle(ResultCode.UNEXPECTED_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

