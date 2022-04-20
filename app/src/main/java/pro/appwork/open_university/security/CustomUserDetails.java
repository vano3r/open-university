package pro.appwork.open_university.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pro.appwork.open_university.model.entity.CustomUser;

import java.util.Collection;
import java.util.List;

import static pro.appwork.open_university.model.enums.UserState.ACTIVE;

@Data
public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromDefaultUser(CustomUser user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getState().equals(ACTIVE),
                user.getState().equals(ACTIVE),
                user.getState().equals(ACTIVE),
                user.getState().equals(ACTIVE),
                user.getAuthorities()
        );
    }
}
