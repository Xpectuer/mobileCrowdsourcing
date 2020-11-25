package edu.zjut.mobilr.authservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
@Configuration
public class CustomUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null)
            return;

        for(ConfigAttribute configAttribute :configAttributes){

            String needRole =configAttribute.getAttribute();
            for(GrantedAuthority ga: authentication.getAuthorities()){
                if(needRole.equals(ga.getAuthority()))
                    return;
            }
            log.error("need role is " + needRole);
        }
       throw new AccessDeniedException("Cannot Access");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;

    }
}
