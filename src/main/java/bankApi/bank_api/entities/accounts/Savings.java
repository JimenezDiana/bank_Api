package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;


@Entity
public class Savings extends Account{

    private Double interestRate = 0.0025;

    //@Embedded
    //@AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance")), @AttributeOverride(name = "amount", column = @Column(name = "amount_minumum_balance"))})
    @DecimalMin(value = "100.00")
    private BigDecimal minimumBalance =  new BigDecimal("1000");
    private Status status;

    private LocalDate checkingInterestRate;

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Double interestRate, BigDecimal minimumBalance, LocalDate checkingInterestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        setInterestRate(interestRate);
        setCheckingInterestRate(checkingInterestRate);
        setMinimumBalance(minimumBalance);

        this.status = Status.ACTIVE;
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Double interestRate, Money minimumBalance) {
    }

    public Savings() {

    }


    /*public void setBalance(BigDecimal balance){
        if(minimumBalance == null){
            minimumBalance =  new Money(new BigDecimal("1000"));
        }
        if(balance.getAmount().compareTo(minimumBalance.getAmount()) < 0){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(new BigDecimal(String.valueOf(balance)));
    }*/

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        if(interestRate > 0.5){
            throw new IllegalArgumentException("Sorry, you're interest rate must be under of 0,5");
        }else{
            this.interestRate = interestRate;
        }

    }

    public LocalDate getCheckingInterestRate(LocalDate localDate) {

        if(checkingInterestRate == null){
           return checkingInterestRate = LocalDate.now();
        }
        return checkingInterestRate;
    }

    public void setCheckingInterestRate(LocalDate checkingInterestRate) {

        this.checkingInterestRate = checkingInterestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {

        /* if(minimumBalance.getAmount().compareTo(new BigDecimal("100")) < 0){
            throw new IllegalArgumentException("You can't have less than 100eur");
        } else {
            this.minimumBalance = minimumBalance;
        }*/
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Money checkInterest(){
        if (LocalDate.now().isAfter(checkingInterestRate.plusYears(1))){
            BigDecimal bigDecimal = getBalance().getAmount().multiply(new BigDecimal(interestRate));
            setBalance((getBalance().increaseAmount(bigDecimal)));
            setCheckingInterestRate(LocalDate.now());
            return getBalance();
        }
        return getBalance();
    }
}