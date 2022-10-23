package bankApi.bank_api.repository.account;

import bankApi.bank_api.entities.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
