package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Checking extends Account{

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance")), @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance"))})
    private Money minimumBalance = new Money(new BigDecimal("250"));
    private BigDecimal monthlyMaintenanceFee = new BigDecimal("12");
    private LocalDate checkingInterestRate;

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String password, Money minimumBalance, BigDecimal monthlyMaintenanceFee, LocalDate checkingInterestRate) {
        super(balance, primaryOwner, secondaryOwner, password);
        setMinimumBalance(minimumBalance);
        setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        setCheckingInterestRate(checkingInterestRate);
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String password) {
        super(balance, primaryOwner, secondaryOwner, password);
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
    }

    public Checking() {

    }

    public void setPrimaryOwner(AccountHolder accountHolder){
        //if(accountHolder.getDateOfBirth().minusYears(24) == accountHolder.getDateOfBirth()){

    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setBalance(Money balance){
        if(minimumBalance == null){
            minimumBalance =  new Money(new BigDecimal("1000"));
        }
        if(balance.getAmount().compareTo(minimumBalance.getAmount()) < 0){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(new BigDecimal(String.valueOf(balance)));
    }
    public void setMinimumBalance(Money minimumBalance) {

        if(minimumBalance.getAmount().compareTo(new BigDecimal("100")) < 0){
            throw new IllegalArgumentException("You can't have less than 100eur");
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getCheckingInterestRate() {
        return checkingInterestRate;
    }

    public Money setCheckingInterestRate(LocalDate checkingInterestRate) {
        this.checkingInterestRate = checkingInterestRate;
        return null;
    }
    public Money checkMonthlyMaintenanceFee(){

        if(Period.between(getCheckingInterestRate().plusMonths(1), LocalDate.now()).getMonths() > 0){
            super.setBalance(getBalance().decreaseAmount(monthlyMaintenanceFee));
            return setCheckingInterestRate(checkingInterestRate.plusMonths(1));
        }
        setCheckingInterestRate(LocalDate.now());



        return getBalance();
}}
