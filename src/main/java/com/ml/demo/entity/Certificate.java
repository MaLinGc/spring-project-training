package com.ml.demo.entity;

import com.ml.demo.enums.AuthName;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "certificate")
public class Certificate implements Serializable {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "assigned")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "auth_name")
    @Enumerated(EnumType.STRING)
    private AuthName authName;

    //本地登录
    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "oauth_id", unique = true)
    private String oauthId;
    @Column(name = "oauth_access_token")
    private String oauthAccessToken;
    @Column(name = "oauth_expires")
    private Long oauthExpires;

    private boolean enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthName getAuthName() {
        return authName;
    }

    public void setAuthName(AuthName authName) {
        this.authName = authName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
    }

    public Long getOauthExpires() {
        return oauthExpires;
    }

    public void setOauthExpires(Long oauthExpires) {
        this.oauthExpires = oauthExpires;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
