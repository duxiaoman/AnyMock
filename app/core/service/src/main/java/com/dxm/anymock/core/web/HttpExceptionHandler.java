package com.dxm.anymock.core.web;

import com.dxm.anymock.common.base.BaseResponse;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import com.dxm.anymock.common.base.utils.MessageUtils;
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
    private MessageUtils messageUtils;

    private BaseResponse generateBaseResponseByErrorCode(ErrorCode errorCode) {
        BaseResponse response = new BaseResponse();
        response.setResultCode(errorCode.getCode());
        response.setResultMsg(messageUtils.getErrorMsg(errorCode));
        return response;
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleBaseException(BaseException e) {
        BaseResponse response = generateBaseResponseByErrorCode(e.getErrorCode());
        logger.warn("{}", response, e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        BaseResponse response = generateBaseResponseByErrorCode(ErrorCode.UNEXPECTED_ERROR);
        logger.error("{}", response, e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

