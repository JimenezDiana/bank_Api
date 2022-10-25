package bankApi.bank_api.services.userService;

import bankApi.bank_api.controller.DTO.AccountDTO;
import bankApi.bank_api.controller.DTO.AdminDTO;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.*;
import bankApi.bank_api.repository.account.*;
import bankApi.bank_api.repository.user.HolderRepository;
import bankApi.bank_api.repository.user.RoleRepository;
import bankApi.bank_api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    HolderRepository holderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    //public Admin createAdmin()
    public AccountHolder createHolderAccount(AccountDTO holderDTO){
        AccountHolder accountHolder = new AccountHolder(holderDTO.getName(), holderDTO.getPassword(), holderDTO.getDateOfBirth(), holderDTO.getAddress(), holderDTO.getMailing());
        userRepository.save(accountHolder);
        roleRepository.save(new Role("USER", accountHolder));
        return accountHolder;
    }

    public Savings createSavingsAccount(AccountDTO savingDto) {
        if(holderRepository.findById(savingDto.getPrimaryOwnerId()).isPresent()){

            Money balance = new Money(new BigDecimal(savingDto.getBalance()));
            AccountHolder primaryOwner = holderRepository.findById(savingDto.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;
            if(savingDto.getSecondaryOwnerId() != null && holderRepository.findById(savingDto.getSecondaryOwnerId()).isPresent()){
                secondaryOwner = holderRepository.findById(savingDto.getSecondaryOwnerId()).get();
            }
            //Money penaltyFee = new Money(new BigDecimal(savingDto.getPenaltyFee()));


            return savingRepository.save(new Savings(balance,primaryOwner, secondaryOwner, savingDto.getInterestRate(), savingDto.getMinimumBalance() ));
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "primary holder does not exist");


        //       AccountHolder accountHolder = holderRepository.findById(savingDto.getPrimaryOwner()).get();
        //       Savings savings = new Savings(savingDto.getBalance(), accountHolder,savingDto.getPenaltyFee(), LocalDate.now(), savingDto.getInterestRate(), savingDto.getMinimumBalance(), savingDto.getSecretKey());
        //       return savingRepository.save(savings);
    }

    public Account createCheckingAccount(AccountDTO checkingDTO){
        if(holderRepository.findById(checkingDTO.getPrimaryOwnerId()).isPresent()){
            Money balance = new Money(new BigDecimal(checkingDTO.getBalance()));
            AccountHolder primaryOwner = holderRepository.findById(checkingDTO.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;

            if(checkingDTO.getSecondaryOwnerId() != null && holderRepository.findById(checkingDTO.getSecondaryOwnerId()).isPresent()){
                secondaryOwner = holderRepository.findById(checkingDTO.getSecondaryOwnerId()).get();
            }

            //Money penaltyFee = new Money(new BigDecimal(checkingDTO.getPenaltyFee()));

            if(Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() >= 24 && Period.between(secondaryOwner.getDateOfBirth(), LocalDate.now()).getYears() >= 24) {
                return checkingRepository.save(new Checking(balance, primaryOwner, secondaryOwner));
            }
            return studentCheckingRepository.save(new StudentChecking(balance, primaryOwner, secondaryOwner));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "it can't resolve");
    }



    public CreditCard createCreditCard(AccountDTO creditDTO) {
        if(holderRepository.findById(creditDTO.getPrimaryOwnerId()).isPresent()){
            Money balance = new Money(new BigDecimal(creditDTO.getBalance()));
            AccountHolder primaryOwner = holderRepository.findById(creditDTO.getPrimaryOwnerId()).get();
            AccountHolder secondaryOwner = null;

            if(creditDTO.getSecondaryOwnerId() != null && holderRepository.findById(creditDTO.getSecondaryOwnerId()).isPresent()){
                secondaryOwner = holderRepository.findById(creditDTO.getSecondaryOwnerId()).get();
            }
            BigDecimal interestRate = null;
            if(creditDTO.getInterestRate()!=null){
            interestRate = new BigDecimal(creditDTO.getInterestRate());}
            BigDecimal creditLimit = creditDTO.getCreditLimit();
            return creditCardRepository.save(new CreditCard(balance,primaryOwner, secondaryOwner, creditLimit, interestRate, creditDTO.getPassword(),creditDTO.getCheckingInterestRate()));
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "primary holder does not exist");
    }

    public void deleteAccount(Long id){
        Account deleteAcc = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong id"));
        accountRepository.deleteById(deleteAcc.getId());
    }

    /*public void deleteUser(Long id){
        User deleteUser = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong id"));
        userRepository.deleteById(deleteUser.getId());
    }*/

    public List<Account> getAllAccounts(){return accountRepository.findAll();}
    public List<User> getAllUsers(){return userRepository.findAll();}

    public Money getAccountBalance(Long id){
        Account acc = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "This id doesn't exists"));
        return acc.getBalance();
    }
    public Account modifyBalance(Long accId, BigDecimal balance){
        Account acc = accountRepository.findById(accId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "this id doesn't exists"));
        acc.setBalance(new Money(balance));
        return accountRepository.save(acc);
    }

    public ThirdParty createThirdParty(String name, String hashKey){
        ThirdParty thirdParty = new ThirdParty(name, hashKey);
        return userRepository.save(thirdParty);
    }

    public Admin createAdmin(String name, String password){
        Admin admin = new Admin(name, passwordEncoder.encode(password));
        userRepository.save(admin);
        roleRepository.save(new Role("ADMIN", admin));
        return admin;
    }
}