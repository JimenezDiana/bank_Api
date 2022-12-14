package bankApi.bank_api.user;

import bankApi.bank_api.controller.DTO.AccountDTO;
import bankApi.bank_api.controller.DTO.AdminDTO;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.*;
import bankApi.bank_api.repository.account.AccountRepository;
import bankApi.bank_api.repository.account.CheckingRepository;
import bankApi.bank_api.repository.account.CreditCardRepository;
import bankApi.bank_api.repository.account.SavingRepository;
import bankApi.bank_api.repository.user.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
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
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Admin admin;
    private Account checkingAccount, savingAccount, creditAccount;
    private AccountHolder holder, holder2;
    private User user;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp(){
        admin = adminRepository.save(new Admin("Diana J", passwordEncoder.encode("Aloha666")));
        //checkingAccount = checkingRepository.save(new Checking(new Money(new BigDecimal("655042")), holder, holder, passwordEncoder.encode("ksdjg406i.")));
        savingAccount = savingRepository.save(new Savings(new Money(new BigDecimal("2540")), holder2, holder2, 0.15, new BigDecimal("8050")));
        creditAccount = creditCardRepository.save(new CreditCard(new Money(new BigDecimal("35423")), holder, holder2,new BigDecimal("10000"), new BigDecimal("0.18"), passwordEncoder.encode("e845ugne"), LocalDate.now()));
        holder = holderRepository.save(new AccountHolder("John Wayne", passwordEncoder.encode("123456b"), LocalDate.of(1905, 2, 15), new Adress("John Wayne St", "Girona", "Spain", "08902"), new Adress("34th, St", "New York", "USA", "010101A")));
        holder2 = holderRepository.save(new AccountHolder("Chomsky", passwordEncoder.encode("Tgvl44f"), LocalDate.of(1960, 9, 21), new Adress("Maria Cristina", "Barcelona", "Spain", "08012"), new Adress("5th, Av", "New York", "USA", "010101A")));
        user = userRepository.save(new User("Ragnar", "12345"));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("get accounts")
    void get_accounts_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(accountRepository.findById(1L).isPresent());
    }

    /*@Test
    @DisplayName("get accounts by id")
    void get_accounts_by_id_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts/{id}").param("id", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(accountRepository.findById(1L).isPresent());
    }*/
    @Test
    @DisplayName("get users")
    void get_users_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(accountRepository.findById(1L).isPresent());
    }

    @Test
    @DisplayName("delete account")
    void delete_accounts_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admin/delete-account/").param("id","2")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andReturn();
        assertTrue(!accountRepository.existsById(2L));
    }

    @Test
    @DisplayName("Create ThirdParty")
    @WithMockUser("diana")
    void create_thirparty_ok() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/admin/third-party").param("name", "Cafeteria Buenos dias").param("hashedKey","945684"))
                .andExpect(status().isCreated()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = jsonNode.get("id");
        Long id = node.asLong();

        assertTrue(thirdPartyRepository.findById(id).isPresent());
        //System.out.println(mvcResult.getResponse().getContentAsString());
        //Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("Cafeteria Buenos dias"));

    }

    @Test
    @DisplayName("Create Savings Account")
    void create_savingAccount_ok() throws Exception {

        AccountDTO savings = new AccountDTO("9000", holder.getId(), holder2.getId(),0.12, new BigDecimal(400));
        savings.setInterestRate(0.15);
        savings.setMinimumBalance(new BigDecimal(250));
        MvcResult mvcResult = mockMvc.perform(post("/admin/create-saving-account").content(objectMapper.writeValueAsString(savings))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();


        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("9000"));
    }

    @Test
    @DisplayName("Create checking account")
    void create_checking_account_ok() throws Exception {
        AccountDTO checking = new AccountDTO("309440", holder.getId(), holder2.getId(), LocalDate.of(1980, 2,11));
        MvcResult mvcResult = mockMvc.perform(post("/admin/student-or-checking-account").content(objectMapper.writeValueAsString(checking))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("309440"));
    }

    @Test
    @DisplayName("Create credit card account")
    void create_credit_card_account_ok() throws Exception {
        AccountDTO credit = new AccountDTO("309440", holder.getId(), holder2.getId(), new BigDecimal(1000), 0.15, "jsjs", LocalDate.of(1990, 2, 3));
        MvcResult mvcResult = mockMvc.perform(post("/admin/student-or-checking-account").content(objectMapper.writeValueAsString(credit))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("309440"));
    }

    @Test
    @DisplayName("get balance")
    void get_balance_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/balance").param("id", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(accountRepository.findById(1L).isPresent());
    }

  /*  @Test
    @DisplayName("change balance")
    void change_balance_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(patch("/admin/change-balance").param("accId","4").param("balance","1000"))
                .andExpect(status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = jsonNode.get("accId");
        Long id = node.asLong();

        assertTrue(accountRepository.findById(id).isPresent());

                }
*/

    @Test
    @DisplayName("get primary owner and secondary owner")
    void getPrimarySecondaryOwner() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/acc-holder/account/primary-secondary/owner").param("id","2").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted()).andReturn();
        assertTrue(holderRepository.findById(2L).isPresent());
    }

    @Test
    @DisplayName("balance holder")
    void balance_holder_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/acc-holder/balance").param("id", "2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(holderRepository.findById(2L).isPresent());

    }
    /*@Test
    @DisplayName("holder interest rate")
    void interest_rate_holder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/acc-holder/balance-interestRate").param("id", "2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        assertTrue(holderRepository.findById(2L).isPresent());

    }*/
    @Test
    @DisplayName("make transfe")
    @WithMockUser("diana")
    void make_transfer_ok() throws Exception {

        MvcResult mvcResult = mockMvc.perform(put("/acc-holder/transfe").param("id", "1").param("recipientId", "2L").param("amount", "5984"))
                .andExpect(status().isCreated()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(mvcResult.getResponse().getContentAsString());
        JsonNode node = jsonNode.get("id");
        Long id = node.asLong();

        assertTrue(holderRepository.findById(id).isPresent());
    }
}
