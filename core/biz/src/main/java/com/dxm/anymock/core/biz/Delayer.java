package com.dxm.anymock.core.biz;

import com.dxm.anymock.common.base.enums.ResultCode;
import com.dxm.anymock.common.base.exception.BizException;

public class Delayer {
    public static void delay(Integer time) {
        try {
            if (time > 0) {
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            throw new BizException(ResultCode.UNEXPECTED_ERROR, e);
        }
    }
}
