package com.dxm.anymock.common.base.util;

import com.dxm.anymock.common.base.enums.ErrorCode;
import com.dxm.anymock.common.base.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

public class ConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConvertUtil.class);

    public static<In, Out> Out convert(In from, Class<Out> outClass) {
        if (from == null) {
            return null;
        }
        Out to = null;
        try {
            to = outClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BaseException(ErrorCode.UNEXPECTED_ERROR, e);
        }
        BeanUtils.copyProperties(from, to);
        return to;
    }

    public static<In, Out> List<Out> convert(List<In> fromList, Class<Out> outClass) {
        List<Out> toList = new LinkedList<>();
        fromList.forEach(from -> toList.add(convert(from, outClass)));
        return toList;
    }
}
