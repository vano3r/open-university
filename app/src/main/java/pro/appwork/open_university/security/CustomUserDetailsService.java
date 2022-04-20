package pro.appwork.open_university.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not fount be email " + email)
        );

        return CustomUserDetails.fromDefaultUser(user);
    }
}
