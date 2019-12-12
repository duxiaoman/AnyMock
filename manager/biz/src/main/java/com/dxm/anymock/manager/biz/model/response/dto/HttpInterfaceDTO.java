package com.dxm.anymock.manager.biz.model.response.dto;

import com.dxm.anymock.common.dal.model.HttpInterfaceBO;

import java.util.List;

public class HttpInterfaceDTO extends HttpInterfaceBO {
    private List<Long> path;

    private Boolean variable;

    public List<Long> getPath() {
        return path;
    }

    public void setPath(List<Long> path) {
        this.path = path;
    }

    public Boolean getVariable() {
        return variable;
    }

    public void setVariable(Boolean variable) {
        this.variable = variable;
    }
}
