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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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
        List<Account> accounts= accountRepository.findByPrimaryOwner(holderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND")));
        accounts.addAll(accountRepository.findBySecondaryOwner(holderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND"))));
        return accounts;
    }

    //////MIRAR BIEN/////--> get balance con las formulas...pero no funciona!!
   /* public Money getBalanceByIdAcc(Long id){
        if (savingRepository.findById(id).isPresent()){
            Savings account = savingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
            account.checkInterest();
            savingRepository.save(account);
            return account.getBalance();
        } else if (checkingRepository.findById(id).isPresent()) {
            Checking account = checkingRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
            account.checkMonthlyMaintenanceFee();
            checkingRepository.save(account);
            return account.getBalance();
        } else if (creditCardRepository.findById(id).isPresent()) {
            CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
            account.checkMonthlyInterest();
            creditCardRepository.save(account);
            return account.getBalance();
        } else {
            Account account = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The account doesn't exist."));
            return account.getBalance();
        }

    }*/

    public void balanceWithInterestAcc(Long id){
            Savings savings = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "this ID is not a saving account"));
            savings.setBalance(getBalanceAccount(id));
            savingRepository.save(savings);
                }


    public Money getBalanceAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found")).getBalance();
    }

   public Money makeTransfe(Long id, Long recipientId, BigDecimal amount){
        Account accountInitial = accountRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "the account with this ID not exist"));
        Account accountFinal = accountRepository.findById(recipientId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "You can't send money to this account because the ID not exist"));
        //if(accountInitial.getPrimaryOwner().getUserName().equals())
       if(accountInitial.getBalance().getAmount().compareTo(amount) < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "in this account you don't have money");
       accountInitial.setBalance(new Money(accountInitial.getBalance().decreaseAmount(amount)));
       accountFinal.setBalance(new Money(accountFinal.getBalance().increaseAmount(amount)));

        Transaction transaction = new Transaction(id, accountInitial, new Money(new BigDecimal(String.valueOf(amount))), recipientId, LocalDate.now(), "savings");
        transactionRepository.save(transaction);
        accountRepository.save(accountFinal);
        accountRepository.save(accountFinal);
        return accountInitial.getBalance();
    }

    //REVISAAAR!! (VOID O TRANSACTION)
    public void accountFraud(Transaction transaction) {
        if (transactionRepository.findById(transaction.getId()).isPresent()){
            if (transaction.getTimeTransaction().until(LocalTime.now(), ChronoUnit.SECONDS) < 1) {
                Account account = accountRepository.findById(transaction.getId()).get();
                account.setStatus(Status.FROZEN);
                accountRepository.save(account);
                throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "You're account is frozen by fraud");

            }
        }
    }

}
