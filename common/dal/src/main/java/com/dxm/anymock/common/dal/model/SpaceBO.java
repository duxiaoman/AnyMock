package com.dxm.anymock.common.dal.model;

import com.dxm.anymock.common.dal.model.enums.AccessAuthority;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.dxm.anymock.common.dal.DalConstants.INTERFACE_CREATION_WHITE_LIST;
import static com.dxm.anymock.common.dal.DalConstants.SPACE_CREATION_WHITE_LIST;

public class SpaceBO {
    public interface Update {}
    public interface Insert {}

    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(groups = {Insert.class, Update.class})
    private String label;

    @NotNull(groups = {Insert.class})
    private Long parentId;

    private AccessAuthority accessAuthority;

    private Integer level;

    private Date gmtCreate;

    private Date gmtModified;

    public Boolean canCreateInterface() {
        return ArrayUtils.contains(INTERFACE_CREATION_WHITE_LIST, this.getLevel());
    }

    public Boolean canCreateSpace() {
        return ArrayUtils.contains(SPACE_CREATION_WHITE_LIST, this.getLevel());
    }

    // getter and setter
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

    public AccessAuthority getAccessAuthority() {
        return accessAuthority;
    }

    public void setAccessAuthority(AccessAuthority accessAuthority) {
        this.accessAuthority = accessAuthority;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
