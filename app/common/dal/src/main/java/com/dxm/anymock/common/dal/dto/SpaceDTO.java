package com.dxm.anymock.common.dal.dto;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class SpaceDTO {

    private Long id;
    private String label;
    private Long parentId;
    private List<SpaceDTO> children;
    private List<Long> path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
