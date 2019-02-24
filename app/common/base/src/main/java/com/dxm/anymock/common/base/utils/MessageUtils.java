package com.dxm.anymock.common.base.utils;

import com.dxm.anymock.common.base.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {
    @Autowired
    private MessageSource messageSource;

    public String getErrorMsg(ErrorCode errorCode) {
        return messageSource.getMessage(
                "ErrorCode." + errorCode.name(), null, LocaleContextHolder.getLocale());
    }
}
