package bankApi.bank_api.controller.DTO;

import bankApi.bank_api.entities.accounts.Money;
import bankApi.bank_api.entities.users.Adress;
import com.sun.istack.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingDto {
    @NotNull
    private Money balance;
    @NotNull
    private Long primaryOwner;
    @NotNull
    private BigDecimal penaltyFee;
    @NotNull
    private Money minimumBalance;
    @NotNull
    private LocalDate createdAccount;
    @NotNull
    private Adress adress;

    @NotNull
    private Double interestRate;


    public SavingDto(Money balance, Long primaryOwner, BigDecimal penaltyFee, Money minimumBalance, LocalDate createdAccount, Adress adress, Double interestRate) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
        this.minimumBalance = minimumBalance;
        this.createdAccount = createdAccount;
        this.adress = adress;
        this.interestRate = interestRate;

    }
    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public LocalDate getCreatedAccount() {
        return createdAccount;
    }

    public void setCreatedAccount(LocalDate createdAccount) {
        this.createdAccount = createdAccount;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}


