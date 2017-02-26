package com.ml.demo.repository;

import com.ml.demo.entity.ResourceRole;
import com.ml.demo.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Role> findRolesByUserId(String userId) {
        String hql = "select r from Role r where r.id in ( select distinct ur.role.id from UserRole ur left join ur.user u where u.id = :userId )";
        return em.createQuery(hql).setParameter("userId", userId).getResultList();
    }

    public List<ResourceRole> findResourceRolesByResourceId(String resourceId) {
        String hql = "from ResourceRole rr left join fetch rr.role where  rr.resource.id = :resourceId";
        return em.createQuery(hql).setParameter("resourceId", resourceId).getResultList();
    }
}
