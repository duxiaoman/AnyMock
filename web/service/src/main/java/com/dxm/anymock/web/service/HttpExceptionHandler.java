package com.dxm.anymock.web.service;

import com.dxm.anymock.common.base.utils.MessageUtils;
import com.dxm.anymock.common.base.BaseResponse;
import com.dxm.anymock.common.base.entity.FieldError;
import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

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
    public BaseResponse handleBaseException(BaseException e) {
        BaseResponse response = generateBaseResponseByErrorCode(e.getErrorCode());
        logger.warn("{}", response, e);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BaseResponse response = new BaseResponse();
        response.setResultCode(ErrorCode.ILLEGAL_ARGUMENT.getCode());
        response.setResultMsg(messageUtils.getErrorMsg(ErrorCode.ILLEGAL_ARGUMENT));

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrorList = new LinkedList<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                FieldError fieldErrorDTO = new FieldError();
                fieldErrorDTO.setField(fieldError.getField());
                fieldErrorDTO.setErrorInfo(fieldError.getDefaultMessage());
                fieldErrorList.add(fieldErrorDTO);
            });
            response.setData(fieldErrorList);
        }
        logger.warn("{}", response, e);
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        BaseResponse response = generateBaseResponseByErrorCode(ErrorCode.UNEXPECTED_ERROR);
        logger.error("{}", response, e);
        return response;
    }
}
