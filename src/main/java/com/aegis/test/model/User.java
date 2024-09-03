package com.aegis.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	private String name;
	private String password;
	private String email;
	private String role;

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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

    @Override
    public String getUsername() {
        return email;
    }
}
