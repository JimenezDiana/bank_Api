# Api Bank by Diana Jiménez

In this java project I built a banking system where there are <b>three types of users</b>:
 * Admins
 * Users
 * Account Holders

There are also <b>four types of accounts</b>:
* StudentChecking
* Checking
* Savings
* CreditCard

For the other hand the accounts have a status that keeps the account operational or not:
* ACTIVE
* FROZEN

The accounts have different properties:

<h3> Checking account properties: </h3> 

* This account hava a balance
* A password
* A primaryOwner
* An optional SecondaryOwner
* A minimumBalance
* A penalty fee
* A monthlyMaintenanceFee
* A creationDate
* A status

<h3> Student Checking accounts properties: </h3>

* This account have a balance
* A password
* One owner and an optional two owners
* A penalty fee
* A creation date
* A status

<h3> Savings accounts properties: </h3>

* This account have a balance
* A password
* One owner and an optional two owner
* Minimum balance
* Penalty fee
* An interest rate

<h3> Credit card accounts properties: </h3>

* This type of account have a balance
* A Primary owner
* An optional secondary owner
* A credit limit
* An interest rate
* A penalty fee

<h2> Users:</h2>

<h3> Account holders properties: </h3>

* This user have a name
* Date of birth
* A primary address
* And an optional mailing address

<h3> Admins properties: </h3>

* Only have a name
__________________________________________________

<b> Third party accounts have a hashed key and a name </b>

____________________________________________________

<b> Savings </b>

* Savings accounts have a default interest rate of 0.0025
* Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
* Savings accounts should have a default minimumBalance of 1000
* Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
________________________________________________________

<b> CreditCards </b>

* CreditCard accounts have a default creditLimit of 100
* CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
* CreditCards have a default interestRate of 0.2
* CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
_________________________________________________________

<b> CheckingAccounts </b>

* When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
* Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12 
____________________________________________________________

<b> PenaltyFee</b>

* The penaltyFee for all accounts should be 40.
* If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically
____________________________________________________________

<b> InterestRate </b>

* Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added to my account after 1 year. When a savings account balance is accessed, you must determine if it has been 1 year or more since either the account was created or since interest was added to the account, and add the appropriate interest to the balance if necessary.
* Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12) then 1% interest will be added to the account monthly. When the balance of a credit card is accessed, check to determine if it has been 1 month or more since the account was created or since interested was added, and if so, add the appropriate interest to the balance.
____________________________________________________________
<h3> Interest and Fees should be applied appropriately </h3>

_______________________________________________________
<b> AccountHolders </b>

* AccountHolders should be able to access their own account balance
* Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner). The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.

__________________________________________________________

<b> Third-Party Users </b>

* There must be a way for third-party users to receive and send money to other accounts.
* Third-party users must be added to the database by an admin.
* In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.
_________________________________________________________

 <h2> 🔷 Paths </h2>

💡<b> Admins can create new accounts. They can create Checking, Savings, or CreditCard Accounts in the next path: </b>
* <u> /admin/create-saving-account </u>
* <u> /admin/student-or-checking-account </u>
* <u> /admin/create-credit-card </u>

💡<b> Admins also can create account holders </b> 
* <u> /admin/create-holder-account </u>

💡 <b> Admins can delete an account </b>
* <u> /admin/delete-account/ </u>

💡 <b> Admins can create a thirdParty </b>
* <u> /admin/third-party </u>

💡 <b> Admins can create an account holders and admins </b>
* <u> /admin/create-holder-account </u>
* <u> /admin/create-admin </u>

💡<b> Admins can modify the balance of the accounts </b>
* <u> /admin/change-balance/ </u>

💡 <b> And can get all the info of the accounts </b>
* <u> /admin/all-accounts </u>
* <u> /admin/accounts/{id} </u>
* <u> /admin/balance </u>
* <u> /admin/users </u>

💡 <b> The account holders can make transference</b>
* <u> /acc-holder/transfe </u>

💡 <b> when an account holder make more than one transaction in less than 1 sec the account will be frozen by fraud </b>
* <u> /acc-holder/fraud" </u>

💡 <b> get mapping account holders: </b>
* <u> /acc-holder/balance-interestRate </u> => The interest rate is automatically applied 
* <u> /acc-holder/account/primary-secondary/owner </u> => get the owners
* <u> /acc-holder/balance </u> => get balance
__________________________________________________

<b> 🔸This project it's about a Java SpringBoot backend</b><br>
<b> 🔸 Everything it's stored in MySQL database</b> <br>
<b> 🔸 Includes authentication with Spring Security</b> <br>
<b> 🔸 And includes unit and integration testing</b>



