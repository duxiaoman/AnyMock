package com.dxm.anymock.web.biz.api.response;

import com.alibaba.fastjson.JSONObject;
import com.dxm.anymock.common.base.entity.HttpInterface;

public class HttpInterfaceDigest extends HttpInterface {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
