package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.EmailToken;

import java.util.Optional;

@Repository
public interface EmailTokenRepository extends JpaRepository<EmailToken, Long> {
    Optional<EmailToken> findByToken(String token);

    void deleteByToken(String token);
}
