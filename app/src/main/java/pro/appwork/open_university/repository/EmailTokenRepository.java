package pro.appwork.open_university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.EmailToken;

import java.util.Optional;

@Repository
public interface EmailTokenRepository extends CrudRepository<EmailToken, Long> {
    Optional<EmailToken> findByToken(String token);
}
