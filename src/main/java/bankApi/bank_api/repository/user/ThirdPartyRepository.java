package bankApi.bank_api.repository.user;

import bankApi.bank_api.entities.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
    Optional<String> findByHashedKey(String hashedKey);
}
