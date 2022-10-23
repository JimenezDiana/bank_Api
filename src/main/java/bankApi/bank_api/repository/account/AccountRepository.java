package bankApi.bank_api.repository.account;

import bankApi.bank_api.entities.accounts.Account;
import bankApi.bank_api.entities.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Override
    List<Account> findAll();

    Optional<Account> findById(Long id);

    List<Account> findByPrimaryOwner(AccountHolder primaryOwner);
    List<Account> findBySecondaryOwner(AccountHolder secondaryOwner);


}
