package com.uooguo.newretail.cloud.framework.util;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Tiangel
 * @date 2018-3-8
 **/
public class User implements UserDetails, CredentialsContainer {

    private Long id;
    private String username;
    private String password;
    private Boolean status;
    private String type;
    private String phoneNumber;
    private String email;
    private String name;
    private Collection<Long> resources = new ArrayList<>();
    private Collection<Long> roles = new ArrayList<>();
    private Collection<GrantedAuthority> grantedAuthorities;
    private Long tenantId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities == null) {
            this.grantedAuthorities = this.getRoles().stream().map(role -> new SimpleGrantedAuthority(String.valueOf(role))).collect(Collectors.toList());
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Collection<Long> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Long> roles) {
        this.roles = roles;
    }

    public Collection<Long> getResources() {
        return resources;
    }

    public void setResources(Collection<Long> resources) {
        this.resources = resources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
