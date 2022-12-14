package com.mnt.wellbook.service.dto;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * A DTO representing a login, with only the public attributes.
 */
public class LoginDTO {

    private String jwtToken;

    private String login;

    private String registerStatus;

    private boolean loginStatus;

    private Collection<? extends GrantedAuthority> role;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }

    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }

    

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "LoginDTO [jwtToken=" + jwtToken + ", login=" + login + ", registerStatus=" + registerStatus + ", role="
                + role + "]";
    }

    

    

    
    
}
