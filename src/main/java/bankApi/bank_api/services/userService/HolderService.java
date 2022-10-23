package bankApi.bank_api.services.userService;

import bankApi.bank_api.Enum.Status;
import bankApi.bank_api.controller.DTO.AccountDTO;
import bankApi.bank_api.entities.Transaction;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.repository.TransactionRepository;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.account.CheckingRepository;
import bankApi.bank_api.repository.account.CreditCardRepository;
import bankApi.bank_api.repository.account.SavingRepository;
import bankApi.bank_api.repository.user.HolderRepository;
import bankApi.bank_api.repository.user.RoleRepository;
import bankApi.bank_api.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class HolderService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    HolderRepository holderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Account> getAccounts(Long id){
        List<Account> accounts= accountRepository.findByPrimaryOwner(holderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "this ID doesn't match with any user")));
        accounts.addAll(accountRepository.findBySecondaryOwner(holderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "this ID doesn't match with any user"))));
        return accounts;
    }



    /* ==>AUN NO FUNCIONA<==
    public Money balanceAccount(Long id){
        Savings savings = new Savings();
        CreditCard creditCard = new CreditCard();
        Checking checking = new Checking();

        if(savingRepository.existsById(id)){
           savings.checkInterest();
           savingRepository.save(savings);
           return savings.getBalance();
        }
        else if(creditCardRepository.findById(id).isPresent()){
           creditCard.checkMonthlyInterest();
           creditCardRepository.save(creditCard);
           return creditCard.getBalance();
        }
        else if(checkingRepository.findById(id).isPresent()){
           checking.checkMonthlyMaintenanceFee();
           checkingRepository.save(checking);
           return checking.getBalance();
        }
        else{
            Account acc = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account not exist"));
            return acc.getBalance();
        }

    }*/

    public Money getBalance(Long id){
        if (savingRepository.existsById(id)){
            return getBalanceSavingAccount(id);
        } else if (checkingRepository.existsById(id)) {
            return getBalanceCheckingAccount(id);
        } else if (creditCardRepository.existsById(id)) {
            return getBalanceCreditCard(id);
        } else {
            Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
            return account.getBalance();
        }

    }

    //////MIRAR BIEN/////
    public Money getBalanceSavingAccount(Long id){
        Savings account = savingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
        account.checkInterest();
        savingRepository.save(account);
        return account.getBalance();
    }

    public Money getBalanceCheckingAccount(Long id){
        Checking account = checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
        account.checkMonthlyMaintenanceFee();
        checkingRepository.save(account);
        return account.getBalance();
    }

    public Money getBalanceCreditCard(Long id){
        CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
        account.checkMonthlyInterest();
        creditCardRepository.save(account);
        return account.getBalance();
    }
    public Money getBalanceAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Account with the given id does not exist")).getBalance();
    }

   public Money transferMoney(Long id, Long recipientId, BigDecimal amount){
        Account accountInitial = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "the account with this ID not exist"));
        Account accountFinal = accountRepository.findById(recipientId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "You can't send money to this account because the ID not exist"));
        //if(accountInitial.getPrimaryOwner().getUserName().equals())
       if(accountInitial.getBalance().getAmount().compareTo(amount) < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "in this account you don't have money");
       accountInitial.setBalance(accountInitial.getBalance().decreaseAmount(amount));
       accountFinal.setBalance(accountFinal.getBalance().increaseAmount(amount));

        Transaction transaction = new Transaction(id, accountInitial, new Money(new BigDecimal(String.valueOf(amount))), recipientId, LocalDate.now(), "savings");
        transactionRepository.save(transaction);
        accountRepository.save(accountFinal);
        accountRepository.save(accountFinal);
        return accountInitial.getBalance();
    }

}
