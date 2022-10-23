package bankApi.bank_api.repository.account;

import bankApi.bank_api.entities.accounts.Savings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface SavingRepository extends JpaRepository<Savings, Long> {
}
