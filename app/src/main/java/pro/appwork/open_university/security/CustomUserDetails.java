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
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.state().equals(ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.state().equals(ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.state().equals(ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return user.state().equals(ACTIVE);
    }
}
