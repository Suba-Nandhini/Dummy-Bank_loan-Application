package com.suba.Bankloanapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
//@Entity
@Data
@Document("Users")
public class User implements UserDetails {
    @Id
    private ObjectId id;

    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User( String username, String password, Role role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=role.getPermissions().stream().map(
            permissions ->new SimpleGrantedAuthority(permissions.name())
        ).collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
