package com.dxm.anymock.manager.web;

import com.dxm.anymock.common.base.ResultCodeTranslator;
import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;
import com.dxm.anymock.manager.biz.model.response.dto.FieldErrorDTO;
import com.dxm.anymock.manager.biz.model.response.BaseResponse;
import com.dxm.anymock.manager.biz.model.response.DataResponse;
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
    private ResultCodeTranslator translator;

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public BaseResponse handleBizException(BizException e) {
        BaseResponse response = new BaseResponse(translator.translate(e.getResultCode()));
        logger.warn("{}", response, e);
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        BaseResponse response = new BaseResponse(translator.translate(ResultCode.UNEXPECTED_ERROR));
        logger.error("{}", response, e);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public DataResponse<List<FieldErrorDTO>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        DataResponse<List<FieldErrorDTO>> response
                = new DataResponse<>(translator.translate(ResultCode.ILLEGAL_ARGUMENT));

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldErrorDTO> fieldErrorDTOList = new LinkedList<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                FieldErrorDTO fieldErrorDTO = new FieldErrorDTO();
                fieldErrorDTO.setField(fieldError.getField());
                fieldErrorDTO.setErrorInfo(fieldError.getDefaultMessage());
                fieldErrorDTOList.add(fieldErrorDTO);
            });
            response.setData(fieldErrorDTOList);
        }
        logger.warn("{}", response, e);
        return response;
    }
}
