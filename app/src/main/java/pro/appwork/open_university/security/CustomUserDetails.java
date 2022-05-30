package pro.appwork.open_university.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pro.appwork.open_university.model.entity.CustomUser;

import java.util.Collection;

import static pro.appwork.open_university.model.enums.UserState.ACTIVE;

public record CustomUserDetails(CustomUser user) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getState().equals(ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getState().equals(ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getState().equals(ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return user.getState().equals(ACTIVE);
    }
}
