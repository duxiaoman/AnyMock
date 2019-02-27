package com.dxm.anymock.common.base.util;

import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.enums.SuccessMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
    @Autowired
    private MessageSource messageSource;

    public String getMsg(ErrorCode errorCode) {
        return messageSource.getMessage(
                "ErrorCode." + errorCode.name(), null, LocaleContextHolder.getLocale());
    }

    public String getMsg(SuccessMsg successMsg) {
        return messageSource.getMessage(
                "SuccessMsg." + successMsg.name(), null, LocaleContextHolder.getLocale());
    }
}
