package bankApi.bank_api.repository.user;

import bankApi.bank_api.entities.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
