package bankApi.bank_api.controller.DTO;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.entities.accounts.Money;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.entities.users.Adress;
import com.sun.istack.NotNull;

import java.time.LocalDate;

public class AccountDTO {
    @NotNull
    private String balance;
    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String name;

    private String amount;
    private LocalDate dateOfBirth;
    private Adress mailing;
    private Adress address;
    private String penaltyFee;
    private Double interestRate;
    private String minimumBalance;

    private Long userId;

    private Long personalId;
    private String password;

    private String status;

    private Long accId;
    private String creditLimit;

    private LocalDate checkingInterestRate;

    private String hashKey;

    public AccountDTO() {
    }

    public AccountDTO(String balance, Long primaryOwnerId, Long secondaryOwnerId, String name, String amount, LocalDate dateOfBirth, Adress mailing, Adress address, String penaltyFee, Double interestRate, String minimumBalance, Long userId, Long personalId, String password, String status, Long accId, String creditLimit, String hashKey, LocalDate checkingInterestRate) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.name = name;
        this.amount = amount;
        this.dateOfBirth = dateOfBirth;
        this.mailing = mailing;
        this.address = address;
        this.penaltyFee = penaltyFee;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.userId = userId;
        this.personalId = personalId;
        this.password = password;
        this.status = status;
        this.accId = accId;
        this.creditLimit = creditLimit;
        this.hashKey = hashKey;
        this.checkingInterestRate = checkingInterestRate;
    }

    public AccountDTO(String money, Long holder, Long holder2, double v, String money1) {
        this.balance = money;
        this.primaryOwnerId = holder;
        this.secondaryOwnerId = holder2;
        this.interestRate = v;
        this.minimumBalance = money1;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public LocalDate getCheckingInterestRate() {
        return checkingInterestRate;
    }

    public void setCheckingInterestRate(LocalDate checkingInterestRate) {
        this.checkingInterestRate = checkingInterestRate;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Adress getMailing() {
        return mailing;
    }

    public void setMailing(Adress mailing) {
        this.mailing = mailing;
    }

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }

    public String getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(String penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(String minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
