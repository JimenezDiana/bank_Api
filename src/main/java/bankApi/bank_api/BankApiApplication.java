package bankApi.bank_api;

import bankApi.bank_api.controller.DTO.AdminDTO;
import bankApi.bank_api.entities.accounts.*;
import bankApi.bank_api.entities.users.AccountHolder;
import bankApi.bank_api.entities.users.Admin;
import bankApi.bank_api.entities.users.Adress;
import bankApi.bank_api.entities.users.Role;
import bankApi.bank_api.repository.account.*;
import bankApi.bank_api.repository.user.AdminRepository;
import bankApi.bank_api.repository.user.HolderRepository;
import bankApi.bank_api.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);

	}
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	HolderRepository holderRepository;
	@Autowired
	SavingRepository savingRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	CheckingRepository checkingRepository;

	@Autowired
	StudentCheckingRepository studentCheckingRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	public void run(String... args) throws Exception {
		Adress adress = new Adress("calle", "ciudad", "pais", "0800");
		Adress adressMailing = new Adress("street", "city", "country", "1000");
		AccountHolder accountHolder = new AccountHolder("Ratoncito Perez", passwordEncoder.encode("sljf"), LocalDate.of(1790, 3, 31), new Adress("calle", "ciudad", "pais", "0800"), new Adress("street", "city", "country", "1000"));
		roleRepository.save(new Role("USER", accountHolder));
		AccountHolder accountHolder2 = new AccountHolder("Wonder Woman", passwordEncoder.encode("sijia"), LocalDate.of(1900, 1, 12), adressMailing, adress);
		roleRepository.save(new Role("USER", accountHolder2));
		AccountHolder accountHolder3 = new AccountHolder("Black Widow", passwordEncoder.encode("jkdwnsfn"), LocalDate.of(1980, 8, 9), adress, adressMailing);
		roleRepository.save(new Role("USER", accountHolder3));
		AccountHolder accountHolderA = new AccountHolder("Jack Sparrow", passwordEncoder.encode("lojoss"), LocalDate.of(1980, 2, 24), adress, adressMailing);
		roleRepository.save(new Role("USER", accountHolderA));
		AccountHolder accountHolderB = new AccountHolder("Trancos", passwordEncoder.encode("Gondor"), LocalDate.of(1200, 7, 14), new Adress("Av de la torre", "Gondor", "tierraMedia", "00000"), new Adress("Rohan street", "Folde Este", "Rohan", "11111"));
		roleRepository.save(new Role("USER", accountHolderB));


		Savings savings = new Savings(new Money(new BigDecimal("800")), accountHolderA, accountHolder, 0.15, new Money(new BigDecimal("200")));
		Savings savingsB = new Savings(new Money(new BigDecimal("400000")), accountHolderB, accountHolderA, 0.15, new Money(new BigDecimal("1000")));
		Savings savingsC = new Savings(new Money(new BigDecimal("797955")), accountHolderB, accountHolder3, 0.15, new Money(new BigDecimal("789")));
		accountRepository.save(savings);
		accountRepository.save(savingsB);
		accountRepository.save(savingsC);


		CreditCard creditCard = new CreditCard(new Money(new BigDecimal("50000")), accountHolder, accountHolderB, passwordEncoder.encode("fñd,"));
		CreditCard creditCardB = new CreditCard(new Money(new BigDecimal("596900")), accountHolderB, accountHolder2, passwordEncoder.encode("fñd,"));
		CreditCard creditCardC = new CreditCard(new Money(new BigDecimal("999099")), accountHolder3, accountHolderA, passwordEncoder.encode("fñd,"));
		CreditCard creditCardD = new CreditCard(new Money(new BigDecimal("100000")), accountHolder2, accountHolder3, passwordEncoder.encode("fñd,"));
		accountRepository.save(creditCard);
		accountRepository.save(creditCardB);
		accountRepository.save(creditCardC);
		accountRepository.save(creditCardD);

		Checking checking = new Checking(new Money(new BigDecimal("583009")), accountHolderB, accountHolder3, passwordEncoder.encode("lkjsfjsd"), new Money(new BigDecimal("50060")), new BigDecimal("40"), LocalDate.now());
		Checking checkingB = new Checking(new Money(new BigDecimal("409354")), accountHolderB, accountHolder3, passwordEncoder.encode("kjlskmc"), new Money(new BigDecimal("5646")), new BigDecimal("40"), LocalDate.of(2022, 06, 4));
		Checking checking1 = new Checking(new Money(new BigDecimal("59594")), accountHolder2, accountHolder, passwordEncoder.encode("ofdjjgd"), new Money(new BigDecimal("60060")), new BigDecimal("12"), LocalDate.now());
		accountRepository.save(checking);
		accountRepository.save(checkingB);
		accountRepository.save(checking1);

		StudentChecking studentChecking = new StudentChecking(new Money(new BigDecimal(4000)), accountHolder, accountHolderA, passwordEncoder.encode("fksl"));
		StudentChecking studentCheckingA = new StudentChecking(new Money(new BigDecimal(10000)), accountHolder2, accountHolder, passwordEncoder.encode("fksl"));
		StudentChecking studentCheckingC = new StudentChecking(new Money(new BigDecimal(200)), accountHolder3, accountHolderB, passwordEncoder.encode("fksl"));

		accountRepository.save(studentChecking);
		accountRepository.save(studentCheckingA);
		accountRepository.save(studentCheckingC);

		Admin admin = new Admin("Admin numero1", passwordEncoder.encode("ksnkl"));
		Admin admin2 = new Admin("Admin numero2",passwordEncoder.encode("kemle"));
		Admin admin3 = new Admin("Admin numero3", passwordEncoder.encode("ihsksa"));
		Admin admin4 = new Admin("Admin numero4", passwordEncoder.encode("klejlfme"));
		roleRepository.save(new Role("ADMIN", admin));
		roleRepository.save(new Role("ADMIN", admin2));
		roleRepository.save(new Role("ADMIN", admin3));
		roleRepository.save(new Role("ADMIN", admin4));
		//adminRepository.save(admin2);
		//adminRepository.save(admin3);
		//adminRepository.save(admin4);
	}
}
