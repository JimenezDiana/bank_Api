package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.Transaction;
import bankApi.bank_api.entities.users.AccountHolder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.TransactionManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "balance")), @AttributeOverride(name = "amount", column = @Column(name = "amount_balance"))})
    private Money balance;

    @ManyToOne
    //@JoinColumn(name = "id_primary_owner")
    private AccountHolder primaryOwner;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "penalty_fee")), @AttributeOverride(name = "amount", column = @Column(name = "amount_penalty_fee"))})
    private final Money penaltyFee = new Money (new BigDecimal("40"));

    @ManyToOne
    //@JoinColumn(name = "id_secondary_owner")
    private AccountHolder secondaryOwner;

    private LocalDate creationDate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactionList;

    private String password;

    private Status status = Status.ACTIVE;


    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String password) {
        this.balance = balance;
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        this.creationDate = LocalDate.now();
        setPassword(password);
        this.status = Status.ACTIVE;
    }

/*    public Account(Money balance, AccountHolder primaryOwner, BigDecimal penaltyFee, LocalDate creationDate) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
    }*/

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
