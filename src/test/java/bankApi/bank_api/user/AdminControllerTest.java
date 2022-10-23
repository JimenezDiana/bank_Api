package bankApi.bank_api.user;

import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.entities.users.Admin;
import bankApi.bank_api.entities.users.Adress;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.account.CheckingRepository;
import bankApi.bank_api.repository.account.CreditCardRepository;
import bankApi.bank_api.repository.account.SavingRepository;
import bankApi.bank_api.repository.user.AdminRepository;
import bankApi.bank_api.repository.user.HolderRepository;
import bankApi.bank_api.repository.user.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    HolderRepository holderRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Admin admin;
    private Account checkingAccount, savingAccount, creditAccount;
    private AccountHolder holder, holder2;

    @BeforeEach
    void setUp(){
        admin = adminRepository.save(new Admin("Diana J", passwordEncoder.encode("Aloha666")));
        checkingAccount = checkingRepository.save(new Checking(new Money(new BigDecimal("655042")), holder, holder, passwordEncoder.encode("ksdjg406i.")));
        savingAccount = savingRepository.save(new Savings(new Money(new BigDecimal("2540")), holder2, holder2, 0.15, new Money(new BigDecimal("8050"))));
        creditAccount = creditCardRepository.save(new CreditCard(new Money(new BigDecimal("35423")), holder, holder2,new BigDecimal("10000"), new BigDecimal("0.25"), passwordEncoder.encode("e845ugne"), LocalDate.now()));
        holder = holderRepository.save(new AccountHolder("John Wayne", passwordEncoder.encode("123456b"), LocalDate.of(1905, 2, 15), new Adress("John Wayne St", "Girona", "Spain", "08902"), new Adress("34th, St", "New York", "USA", "010101A")));
        holder2 = holderRepository.save(new AccountHolder("Judit Butler", passwordEncoder.encode("Tgvl44f"), LocalDate.of(1960, 9, 21), new Adress("Maria Cristina", "Barcelona", "Spain", "08012"), new Adress("5th, Av", "New York", "USA", "010101A")));


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
/*
    @Test
    @DisplayName("get accounts")
    void getAccounts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }*/



}
