package com.dxm.anymock.common.base;

import com.dxm.anymock.common.base.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResultCodeTranslator {

    @Autowired
    private MessageSource messageSource;

    public Map<String, String> translate(ResultCode resultCode) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("resultCode", resultCode.getCode());
        resultMap.put("resultMsg", messageSource.getMessage(
                "ResultCode." + resultCode.name(), null, LocaleContextHolder.getLocale()));
        return resultMap;
    }
}
