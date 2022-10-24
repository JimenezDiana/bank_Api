package bankApi.bank_api.controller.controllerUser;
import bankApi.bank_api.controller.DTO.AccountDTO;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.entities.users.Admin;
import bankApi.bank_api.entities.users.ThirdParty;
import bankApi.bank_api.entities.users.User;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.user.UserRepository;
import bankApi.bank_api.services.userService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
    public class AdminController {

        @Autowired

        UserRepository userRepository;
        @Autowired
        AdminService adminService;

        @Autowired
        AccountRepository accountService;

        @PostMapping("/admin/create-saving-account")
        @ResponseStatus(HttpStatus.CREATED)
        public Savings createSavingsAccount(@RequestBody AccountDTO savingsDTO) {
            return adminService.createSavingsAccount(savingsDTO);
        }

        @PostMapping("/admin/create-holder-account")
        @ResponseStatus(HttpStatus.CREATED)
        public AccountHolder createHolderAccount(@RequestBody AccountDTO adminDTO) {
            return adminService.createHolderAccount(adminDTO);
        }

        @PostMapping("/admin/student-or-checking-account")
        @ResponseStatus(HttpStatus.CREATED)
        public Account createCheckingAccount(@RequestBody AccountDTO checkingDTO) {
            return adminService.createCheckingAccount(checkingDTO);
        }

        @PostMapping("/admin/create-credit-card")
        @ResponseStatus(HttpStatus.CREATED)
        public CreditCard createCreditCard(@RequestBody AccountDTO creditDTO) {
            return adminService.createCreditCard(creditDTO);
        }


        @GetMapping("/admin/accounts")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public List<Account> getAllAccounts(){return accountService.findAll();}

        @GetMapping("/admin/users")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public List<User> getAllUsers(){return adminService.getAllUsers();}

        @GetMapping("/admin/balance")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public Money getBalance(@RequestParam Long id){
            return adminService.getAccountBalance(id);
        }

        @PatchMapping("/admin/change-balance/")
        @ResponseStatus(HttpStatus.CREATED)
        public Account modifyBalance(@RequestParam Long accId,@RequestParam BigDecimal balance) {
            return adminService.modifyBalance(accId, balance);
        }

        @GetMapping("/admin/accounts/{id}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public Optional<Account> getById(@PathVariable Long id){
            return accountService.findById(id);
        }

        @DeleteMapping("/admin/delete-account/")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void deleteAccount(@RequestParam Long id){
            adminService.deleteAccount(id);
        }

        @DeleteMapping("/admin/delete-user/")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void deleteUser(@RequestParam Long id){
            adminService.deleteUser(id);
        }

        @GetMapping("/admin/all-accounts")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void getAccounts(){
            adminService.getAllAccounts();
        }

        @PostMapping("/admin/third-party")
        @ResponseStatus(HttpStatus.CREATED)
        public ThirdParty createThirdParty(@RequestParam String name, @RequestParam String hashKey){
            return adminService.createThirdParty(name, hashKey);
        }

        @PostMapping("/admin/create-admin")
        @ResponseStatus(HttpStatus.CREATED)
        public Admin createAdmin(@RequestParam String name, @RequestParam String password){
            return adminService.createAdmin(name, password);
        }
    }

