package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.repository.UserRepository;
import pro.appwork.open_university.service.UserService;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createNew(CustomUser user) {
        user.password(passwordEncoder.encode(user.password()));
        repository.save(user);
    }
}
