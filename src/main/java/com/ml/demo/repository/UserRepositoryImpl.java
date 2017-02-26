package com.ml.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

}