package com.ml.demo.security;

import com.ml.demo.entity.Role;
import com.ml.demo.entity.Certificate;
import com.ml.demo.entity.User;
import com.ml.demo.repository.RoleRepository;
import com.ml.demo.repository.CertificateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Certificate certificate = certificateRepository.findByUsername(username);
        if (certificate == null) {
            this.logger.debug("Query returned no results for user \'{}\'", username);
            throw new UsernameNotFoundException(String.format("Username {} not found", username));
        }
        User user = certificate.getUser();
        Collection<GrantedAuthority> authorities = obtainGrantedAuthorities(user);
        return new org.springframework.security.core.userdetails.User(certificate.getUsername(), certificate.getPassword(), certificate.isEnabled(), true, true, true, authorities);
    }

    private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        List<Role> userRoles = roleRepository.findRolesByUserId(user.getId());
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : userRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }


}
