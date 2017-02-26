package com.ml.demo.service;

import com.alibaba.fastjson.JSON;
import com.github.wenhao.jpa.Specifications;
import com.ml.commons.utils.IdUtil;
import com.ml.demo.entity.Certificate;
import com.ml.demo.entity.User;
import com.ml.demo.repository.CertificateRepository;
import com.ml.demo.repository.UserRepository;
import com.ml.demo.vo.RegisterUser;
import com.ml.demo.vo.Searchable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Service
public class UserService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> findBySearchable(Searchable searchable, Pageable pageable) {

        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return null;
            }
        }, pageable);
    }

    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    public void delete(String id) {
        User userProfile = findOne(id);
        if (userProfile != null)
            userRepository.delete(id);
    }

    /**
     * 用户注册
     *
     * @param registerUser
     * @throws Exception
     */
    public Certificate saveNewUserWithCertificate(RegisterUser registerUser) throws Exception {
        if (registerUser == null)
            throw new Exception("数据错误");
        User user = registerUser.getUser();
        Certificate certificate = registerUser.getCertificate();
        if (user == null || certificate == null)
            throw new Exception("数据错误");

        addUser(user);
        return saveUserCertificate(user, certificate);
    }

    /**
     * 添加新登录凭证
     *
     * @param user
     * @param certificate
     * @return
     * @throws Exception
     */
    public Certificate addNewUserCertificate(User user, Certificate certificate) throws Exception {
        if (user == null || StringUtils.isBlank(user.getId()) || certificate == null)
            throw new Exception("数据错误");
        User db = findOne(user.getId());
        if (db == null)
            throw new Exception("用户不存在");
        return saveUserCertificate(user, certificate);
    }

    public void addUser(User user) throws Exception {
        user.setId(IdUtil.randomUUID());
        user.setCreateTime(new Date());
        userRepository.save(user);
    }

    public void updateUser(User user) throws Exception {
        if (user == null || StringUtils.isBlank(user.getId()))
            throw new Exception("用户不存在");
        User db = userRepository.findOne(user.getId());
        if (db == null) {
            logger.info("update user '{}' failed!", JSON.toJSONString(user));
            throw new Exception("用户不存在");
        }
        db.setEmail(user.getEmail());
        db.setUpdateTime(new Date());
        db.setAddress(user.getAddress());
        db.setGender(user.getGender());
        db.setAge(user.getAge());
        db.setName(user.getName());
        userRepository.save(db);
    }

    /**
     * 添加用户登录认证信息
     *
     * @param user        dbUser
     * @param certificate 认证信息
     * @throws Exception
     */
    private Certificate saveUserCertificate(User user, Certificate certificate) throws Exception {
        if (user == null || StringUtils.isBlank(user.getId()))
            throw new Exception("用户个人信息数据错误");
        if (certificate == null || certificate.getAuthName() == null)
            throw new Exception("用户登录凭证数据错误");
        User userProfile = findOne(user.getId());
        if (userProfile == null)
            throw new Exception("用户个人信息数据错误");
        switch (certificate.getAuthName()) {
            case local:
                saveLocalCertificate(userProfile, certificate);
                break;
            case qq:
                break;
            case weibo:
                break;
            case weixin:
                break;
            default:
                throw new Exception("注册方式不支持");
        }
        return certificate;
    }

    private Certificate saveLocalCertificate(User user, Certificate certificate) throws Exception {
        if (StringUtils.isBlank(certificate.getUsername()) || StringUtils.isBlank(certificate.getPassword()))
            throw new Exception("用户名或者为空");
        Certificate db = certificateRepository.findByUsername(certificate.getUsername());
        if (db != null)
            throw new Exception("用户名已存在");
        certificate.setId(IdUtil.randomUUID());
        certificate.setEnabled(true);
        certificate.setPassword(passwordEncoder.encode(certificate.getPassword()));
        certificate.setUser(user);
        return certificateRepository.save(certificate);
    }
}
