package com.ml.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ResourceRoleId implements Serializable {

    @Column(name = "role_id", length = 32)
    private String roleId;
    @Column(name = "resource_id", length = 32)
    private String resourceId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRoleId that = (ResourceRoleId) o;

        if (getRoleId() != null ? !getRoleId().equals(that.getRoleId()) : that.getRoleId() != null) return false;
        return getResourceId() != null ? getResourceId().equals(that.getResourceId()) : that.getResourceId() == null;
    }

    @Override
    public int hashCode() {
        int result = getRoleId() != null ? getRoleId().hashCode() : 0;
        result = 31 * result + (getResourceId() != null ? getResourceId().hashCode() : 0);
        return result;
    }
}
