package com.ml.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

@Service
public class MySecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        super.setAccessDecisionManager(myAccessDecisionManager);
        super.setAuthenticationManager(authenticationManager);
        /* 如果设为true ,除了在权限列表中的url,其他url均会报错*/
//        super.setRejectPublicInvocations(true);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        /*
        * 1.获取所有权限
        *       fi 调用 Collection<ConfigAttribute> attributes = this.obtainSecurityMetadataSource().getAttributes(object);
        *       if (attributes == null || attributes.isEmpty()) return null;
        * 2.判断是否要认证
        *       authenticateIfRequired() {
        *           authentication = authenticationManager.authenticate(authentication);
        *       }
        * 3.尝试授权
        *       this.accessDecisionManager.decide(authenticated, object, attributes);
        *
        * */

        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.finallyInvocation(token);
        }

        super.afterInvocation(token, null);
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.mySecurityMetadataSource;
    }


}
