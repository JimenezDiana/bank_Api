package bankApi.bank_api.controller.controllerUser;

import bankApi.bank_api.entities.accounts.Money;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.user.UserRepository;
import bankApi.bank_api.services.userService.AdminService;
import bankApi.bank_api.services.userService.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



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


}
