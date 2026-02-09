package com.software.mywordbox.library.security;

import com.software.mywordbox.domain.auth.user.impl.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Spring Security'nin kullanıcı bilgilerini (UserDetails) tanıyabilmesi için özel bir
 * kullanıcı detay sınıfı oluşturmak.
 */
public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public String getId() {
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Role olmadığı için default olarak ROLE_USER veriyoruz
        // Eğer role eklerseniz: user.getRole().name()
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress(); // Email ile login yapılacak
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesap süresinin dolup dolmadığını belirtir
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesap kilitli mi
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Kimlik bilgileri süresi dolmuş mu
    }

    @Override
    public boolean isEnabled() {
        return true; // Kullanıcı aktif mi
    }
}