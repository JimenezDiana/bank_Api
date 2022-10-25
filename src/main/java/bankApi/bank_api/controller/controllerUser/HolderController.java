package bankApi.bank_api.controller.controllerUser;

import bankApi.bank_api.entities.Transaction;
import bankApi.bank_api.entities.accounts.Account;
import bankApi.bank_api.entities.accounts.Money;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.user.UserRepository;
import bankApi.bank_api.services.userService.AdminService;
import bankApi.bank_api.services.userService.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class HolderController {

    @Autowired

    UserRepository userRepository;
    @Autowired
    AdminService adminService;

    @Autowired
    AccountRepository accountService;

    @Autowired
    HolderService holderService;

    @GetMapping("/acc-holder/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getBalance(@RequestParam Long id){
        return holderService.getBalanceAccount(id);
    }

    @GetMapping("/acc-holder/account/primary-secondary/owner")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Account> getAccounts(@RequestParam Long id){
        return holderService.getAccounts(id);
    }

    @GetMapping("/acc-holder/balance-interestRate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money getBalanceWithInterest(@RequestParam Long id){
       return holderService.balanceWithInterestAcc(id);
    }

    @PutMapping("/acc-holder/transfe")
    @ResponseStatus(HttpStatus.CREATED)
    public Money transfe(@RequestParam Long id, @RequestParam Long recipientId, @RequestParam BigDecimal amount){
        return holderService.makeTransfe(id, recipientId, amount);
    }



}
