package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.users.AccountHolder;

import javax.persistence.Entity;


@Entity
public class StudentChecking extends Account{

    private Status status;

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        this.status = Status.ACTIVE;
    }


    public StudentChecking(Status status) {
        setStatus(status);
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
    }

    public StudentChecking() {

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
