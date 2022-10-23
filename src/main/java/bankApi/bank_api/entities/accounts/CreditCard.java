package bankApi.bank_api.entities.accounts;

import bankApi.bank_api.entities.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{

    //@Embedded
    //@AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "currency_minim_balance")), @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance"))})
    @DecimalMax(value = "1000000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal("100");

    private BigDecimal interestRate = new BigDecimal("0.2");

    private LocalDate checkingInterestRate;

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate,  String secretKey, LocalDate checkingInterestRate) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
        this.creditLimit = creditLimit;
        setInterestRate(interestRate);
        setCheckingInterestRate(checkingInterestRate);
    }

    public CreditCard(Money balance, AccountHolder primaryOwner,  AccountHolder secondaryOwner, String secretKey) {
        super(balance, primaryOwner, secondaryOwner, secretKey);
    }

    public CreditCard(BigDecimal creditLimit, AccountHolder primaryOwner, BigDecimal interestRate, AccountHolder secondaryOwner, Money limit, Money rate) {
        this.creditLimit = creditLimit;
        setInterestRate(interestRate);
    }

    public CreditCard() {
    }

    public BigDecimal getCreditLimit() {
        return this.creditLimit;
    }

    /*public void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit.getAmount().compareTo(new BigDecimal("100000")) > 0) {
            throw new IllegalArgumentException("You can't hava more than 100000");
        } else if (creditLimit.getAmount().compareTo(new BigDecimal("0")) < 0) {
            throw new IllegalArgumentException("You can't have less than 1eur");
        } else {
            this.creditLimit = creditLimit;
        }
    }*/

    public BigDecimal getInterestRate() {
        return interestRate;
    }

   /* public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.getAmount().compareTo(new BigDecimal("0.1")) < 0) {
            throw new IllegalArgumentException("The interest rate can not be less than 0.1");
        } else if (interestRate.getAmount().compareTo(new BigDecimal("0.2")) > 0) {
            throw new IllegalArgumentException("The interest rate must be between 0.2 ant 0.1");
        } else {
            this.interestRate = interestRate;
        }
    }*/

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(new BigDecimal(0.1)) < 0) {
            throw new IllegalArgumentException("The interest rate can not be less than 0.1");
        } else if (interestRate.compareTo(new BigDecimal(0.2)) > 0) {
            throw new IllegalArgumentException("The interest rate must be between 0.2 ant 0.1");
        } else {
            this.interestRate = interestRate;
        }
    }

    public LocalDate getCheckingInterestRate() {
        return checkingInterestRate;
    }

    public void setCheckingInterestRate(LocalDate checkingInterestRate) {
        this.checkingInterestRate = checkingInterestRate;
    }

    public Money checkMonthlyInterest(){
        BigDecimal bigDec =  interestRate.divide(BigDecimal.valueOf(12),4, RoundingMode.HALF_EVEN);
        if (Period.between(getCheckingInterestRate().plusMonths(1), LocalDate.now()).getMonths() > 0){
            BigDecimal bigDecimal = getBalance().getAmount().multiply(bigDec);
            super.setBalance((getBalance().decreaseAmount(bigDec)));
            setCheckingInterestRate(checkingInterestRate.plusMonths(1));
        }
        setCheckingInterestRate(LocalDate.now());
        return getBalance();
    }
}
