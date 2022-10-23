package bankApi.bank_api.repository.user;

import bankApi.bank_api.entities.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HolderRepository extends JpaRepository<AccountHolder, Long> {
    Optional<AccountHolder> findById(Long id);
    //Optional<AccountHolder> findByName(String name);
}
