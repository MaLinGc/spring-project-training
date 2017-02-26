package com.ml.demo.service;

import com.ml.commons.base.BaseServiceTest;
import com.ml.commons.utils.IdUtil;
import com.ml.demo.entity.Certificate;
import com.ml.demo.entity.User;
import com.ml.demo.enums.AuthName;
import com.ml.demo.repository.CertificateRepository;
import com.ml.demo.repository.UserRepository;
import com.ml.demo.vo.RegisterUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    private User user;
    private Certificate certificate;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CertificateRepository certificateRepository;

    @Test
    public void saveNewUserWithCertificate() throws Exception {
        user = new User();
        user.setName("administrator");
        user.setAge(30);
        user.setGender("男");
        user.setEmail("maling@scbit.org");
        user.setAddress("科苑路1278号");

        certificate = new Certificate();
        certificate.setAuthName(AuthName.local);
        certificate.setUsername("administrator@scbit.org");
        certificate.setPassword("111111");

        RegisterUser registerUser = new RegisterUser();
        registerUser.setUser(user);
        registerUser.setCertificate(certificate);

        userService.saveNewUserWithCertificate(registerUser);
    }

    @Test
    public void addNewUserCertificate() throws Exception {

    }

    @Test
    public void addUserProfile() throws Exception {

    }

    @Test
    public void updateUserProfile() throws Exception {

    }


    @Test
    public void saveUserCertificate() throws Exception {

        user = new User();
        user.setId("a3c4afc966ea4bc58d61556f5564e130");
//        user.setName("maling");
//        user.setAge(18);
//        user.setGender("男");
//        user.setEmail("maling@scbit.org");
//        user.setAddress("科苑路1278号");

        certificate = new Certificate();
        certificate.setId(IdUtil.randomUUID());
        certificate.setAuthName(AuthName.local);
        certificate.setUsername("maling@scbit.org");
        certificate.setPassword("111111");

        certificate.setUser(user);
        certificateRepository.save(certificate);
    }

}