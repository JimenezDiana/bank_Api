package bankApi.bank_api.repository.account;

import bankApi.bank_api.entities.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<Checking, Long> {
}
