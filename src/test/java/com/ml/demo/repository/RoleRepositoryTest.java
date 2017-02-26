package com.ml.demo.repository;

import com.ml.commons.base.BaseServiceTest;
import com.ml.demo.entity.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleRepositoryTest extends BaseServiceTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findRolesByUserId() throws Exception {
        List<Role> roles = roleRepository.findRolesByUserId("");
        for (Role role : roles) {
            System.out.println(role.getName());
        }
    }


}