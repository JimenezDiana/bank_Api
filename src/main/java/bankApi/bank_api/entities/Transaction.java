package bankApi.bank_api.entities;

import bankApi.bank_api.entities.accounts.Account;
import bankApi.bank_api.entities.accounts.Money;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Embedded
    private Money quantity;

    private Long recipientId;

    private LocalDate timeTransaction;

    private String TypeOfTransaction;

    public Money getQuantity(){return quantity;}

    public void setQuantity (Money quantity){
        this.quantity = quantity;
        this.timeTransaction = LocalDate.now();
    }

    public Transaction(){}

    public Transaction(Account account){
        this.account = account;
    }

    public LocalDate getTimeTransaction(){
        return timeTransaction;
    }

    public Transaction(Long id, Account account, Money quantity, Long recipientId, LocalDate timeTransaction, String typeOfTransaction) {
        this.id = id;
        this.account = account;
        this.quantity = quantity;
        this.recipientId = recipientId;
        this.timeTransaction = timeTransaction;
        TypeOfTransaction = typeOfTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public void setTimeTransaction(LocalDate timeTransaction) {
        this.timeTransaction = timeTransaction;
    }

    public String getTypeOfTransaction() {
        return TypeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        TypeOfTransaction = typeOfTransaction;
    }
}

