package bankApi.bank_api.controller.DTO;

import bankApi.bank_api.entities.users.Adress;
import com.sun.istack.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdminDTO {

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long primaryOwner;
    private Long secondaryOwner;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private Adress adress;
    private BigDecimal penaltyFee;
    @NotNull
    private Double interesRate;

    @NotNull
    private BigDecimal minBalance;
    @NotNull
    private LocalDate dateOfBirth;

    public AdminDTO(Long id, String name,Long primaryOwner, BigDecimal balance, Adress adress, BigDecimal penaltyFee, Double interesRate,BigDecimal minBalance, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.primaryOwner = primaryOwner;
        this.balance = balance;
        this.adress = adress;
        this.penaltyFee = penaltyFee;
        this.interesRate = interesRate;
        this.minBalance = minBalance;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Long getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Long secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Double getInteresRate() {
        return interesRate;
    }

    public void setInteresRate(Double interesRate) {
        this.interesRate = interesRate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
