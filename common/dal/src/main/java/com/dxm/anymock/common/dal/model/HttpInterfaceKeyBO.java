package com.dxm.anymock.common.dal.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class HttpInterfaceKeyBO {
    public interface Insert {}
    public interface Update {}

    @NotBlank(groups = {HttpInterfaceKeyBO.Insert.class, HttpInterfaceKeyBO.Update.class})
    @Pattern(regexp = "^/.*", groups = {HttpInterfaceKeyBO.Insert.class, HttpInterfaceKeyBO.Update.class})
    private String requestUri;

    @NotNull(groups = {HttpInterfaceKeyBO.Insert.class, HttpInterfaceKeyBO.Update.class})
    private String requestMethod;

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
