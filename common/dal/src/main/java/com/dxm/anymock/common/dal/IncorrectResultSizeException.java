package com.dxm.anymock.common.dal;

import com.dxm.anymock.common.base.exception.SysException;

public class IncorrectResultSizeException extends SysException {
    public IncorrectResultSizeException(int expectValue) {
        super("ExpectValue = " + expectValue);
    }
}
