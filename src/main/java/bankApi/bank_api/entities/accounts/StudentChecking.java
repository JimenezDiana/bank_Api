package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.users.AccountHolder;

import javax.persistence.Entity;


@Entity
public class StudentChecking extends Account {


    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String password) {
        super(balance, primaryOwner, secondaryOwner, password);

    }


    public StudentChecking(Status status) {
        setStatus(status);
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
    }

    public StudentChecking() {

    }

}
