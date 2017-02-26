package com.ml.demo.vo;

import com.ml.demo.entity.Certificate;
import com.ml.demo.entity.User;

public class RegisterUser {

    private User user;
    private Certificate certificate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }
}
