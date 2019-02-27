package com.dxm.anymock.web.biz.api.response;

import com.dxm.anymock.common.base.entity.HttpInterface;

import java.util.List;

public class HttpInterfaceDetail extends HttpInterface {
    private List<Long> path;

    public List<Long> getPath() {
        return path;
    }

    public void setPath(List<Long> path) {
        this.path = path;
    }
}
