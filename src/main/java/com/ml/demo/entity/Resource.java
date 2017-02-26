package com.ml.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private String id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "res_type")
    private String resType;
    @Column(unique = true, nullable = false)
    private String resString;
    @Column(name = "request_method")
    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resource")
    private Set<ResourceRole> resourceRoles = new HashSet<>(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ResourceRole> getResourceRoles() {
        return resourceRoles;
    }

    public void setResourceRoles(Set<ResourceRole> resourceRoles) {
        this.resourceRoles = resourceRoles;
    }
}
