package com.ml.demo.security;

import com.ml.demo.entity.Resource;
import com.ml.demo.entity.ResourceRole;
import com.ml.demo.repository.ResourceRepository;
import com.ml.demo.repository.RoleRepository;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private RoleRepository roleRepository;

    /* 资源url, 所需角色集合map*/
    private static Map<AntPathRequestMatcher, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<>();
            List<Resource> resources = resourceRepository.findAll();
            for (Resource resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                List<ResourceRole> resourceRoles = roleRepository.findResourceRolesByResourceId(resource.getId());
                for (ResourceRole resourceRole : resourceRoles) {
                    ConfigAttribute attribute = new SecurityConfig("ROLE_" + resourceRole.getRole().getName());
                    configAttributes.add(attribute);
                }
                resourceMap.put(new AntPathRequestMatcher(resource.getResString(), resource.getRequestMethod() != null ? resource.getRequestMethod().name() : null), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        if (resourceMap == null)
            loadResourceDefine();
        for (Map.Entry<AntPathRequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            if (entry.getKey().matches(request))
                return entry.getValue();
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
