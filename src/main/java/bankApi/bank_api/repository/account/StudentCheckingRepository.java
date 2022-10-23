package bankApi.bank_api.repository.account;

import bankApi.bank_api.entities.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
}
