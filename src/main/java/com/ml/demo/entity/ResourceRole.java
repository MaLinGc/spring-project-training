package com.ml.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "resource_role")
public class ResourceRole implements Serializable {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "resourceId", column = @Column(name = "resource_id", length = 32)),
            @AttributeOverride(name = "roleId", column = @Column(name = "role_id", length = 32))
    })
    private ResourceRoleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", insertable = false, updatable = false)
    private Resource resource;

    public ResourceRoleId getId() {
        return id;
    }

    public void setId(ResourceRoleId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
