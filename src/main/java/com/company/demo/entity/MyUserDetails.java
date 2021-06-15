package com.company.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Usuario user;
    
    public MyUserDetails(Usuario user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<OpcionMenu> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
        for (OpcionMenu rol : roles) {
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
         
        return authorities;
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled();
    }

	@Override
	public String toString() {
		return "MyUserDetails [user=" + user + "]";
	}
}
