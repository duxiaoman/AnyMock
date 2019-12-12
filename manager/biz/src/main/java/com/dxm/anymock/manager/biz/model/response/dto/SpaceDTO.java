package com.dxm.anymock.manager.biz.model.response.dto;

import com.dxm.anymock.common.dal.model.SpaceBO;

import java.util.List;

public class SpaceDTO extends SpaceBO {
    private List<SpaceDTO> children;
    private List<Long> path;
    private Boolean allowCreateInterface;
    private Boolean allowCreateSpace;
    private Boolean variable;

    public List<SpaceDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SpaceDTO> children) {
        this.children = children;
    }

    public List<Long> getPath() {
        return path;
    }

    public void setPath(List<Long> path) {
        this.path = path;
    }

    public Boolean getAllowCreateInterface() {
        return allowCreateInterface;
    }

    public void setAllowCreateInterface(Boolean allowCreateInterface) {
        this.allowCreateInterface = allowCreateInterface;
    }

    public Boolean getAllowCreateSpace() {
        return allowCreateSpace;
    }

    public void setAllowCreateSpace(Boolean allowCreateSpace) {
        this.allowCreateSpace = allowCreateSpace;
    }

    public Boolean getVariable() {
        return variable;
    }

    public void setVariable(Boolean variable) {
        this.variable = variable;
    }
}
