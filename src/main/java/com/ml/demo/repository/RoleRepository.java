package com.ml.demo.repository;

import com.ml.demo.entity.ResourceRole;
import com.ml.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findRolesByUserId(String userId);

    List<ResourceRole> findResourceRolesByResourceId(String resourceId);
}
