package bankApi.bank_api.user;

import bankApi.bank_api.controller.DTO.AccountDTO;
import bankApi.bank_api.controller.DTO.AdminDTO;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.entities.users.Admin;
import bankApi.bank_api.entities.users.Adress;
import bankApi.bank_api.entities.users.ThirdParty;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.account.CheckingRepository;
import bankApi.bank_api.repository.account.CreditCardRepository;
import bankApi.bank_api.repository.account.SavingRepository;
import bankApi.bank_api.repository.user.AdminRepository;
import bankApi.bank_api.repository.user.HolderRepository;
import bankApi.bank_api.repository.user.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp(){
        admin = adminRepository.save(new Admin("Diana J", passwordEncoder.encode("Aloha666")));
        checkingAccount = checkingRepository.save(new Checking(new Money(new BigDecimal("655042")), holder, holder, passwordEncoder.encode("ksdjg406i.")));
        savingAccount = savingRepository.save(new Savings(new Money(new BigDecimal("2540")), holder2, holder2, 0.15, new Money(new BigDecimal("8050"))));
        creditAccount = creditCardRepository.save(new CreditCard(new Money(new BigDecimal("35423")), holder, holder2,new BigDecimal("10000"), new BigDecimal("0.18"), passwordEncoder.encode("e845ugne"), LocalDate.now()));
        holder = holderRepository.save(new AccountHolder("John Wayne", passwordEncoder.encode("123456b"), LocalDate.of(1905, 2, 15), new Adress("John Wayne St", "Girona", "Spain", "08902"), new Adress("34th, St", "New York", "USA", "010101A")));
        holder2 = holderRepository.save(new AccountHolder("Judit Butler", passwordEncoder.encode("Tgvl44f"), LocalDate.of(1960, 9, 21), new Adress("Maria Cristina", "Barcelona", "Spain", "08012"), new Adress("5th, Av", "New York", "USA", "010101A")));


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /*@Test
    @DisplayName("get accounts")
    void get_accounts_ok() throws Exception {
1   º
        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
    }
    */

    @Test
    @DisplayName("delete account")
    void delete_accounts_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admin/delete-account/").param("id","2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(!accountRepository.existsById(2L));
    }

    /*@Test
    @DisplayName("Create ThirdParty")
    void create_thirparty_ok() throws Exception {
        ThirdParty thirdParty = new ThirdParty("Cafeteria Buenos dias","29p4823rm");
        MvcResult mvcResult = mockMvc.perform(post("/admin/third-party").contentType(String.valueOf(thirdParty)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        Assertions.assertTrue(mvcResult.getRequest().getContentAsString().contains("Cafeteria Buenos dias"));
    } --->NO FUNCIONA<--- */

    @Test
    @DisplayName("Create Savings Account")
    void create_savingAccount_ok() throws Exception {
        Savings savings = new Savings(new Money(new BigDecimal("9000")), holder, holder2,0.12, new Money(new BigDecimal("400")));
        savings.setInterestRate(0.15);
        savings.setMinimumBalance(new BigDecimal(250));
        MvcResult mvcResult = mockMvc.perform(post("/admin/create-saving-account").content(objectMapper.writeValueAsString(savings)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(holder.getId().toString()));
    }


}
