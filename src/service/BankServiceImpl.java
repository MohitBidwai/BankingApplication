package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import Repository.AccountRepository;
import Repository.CustomerRepository;
import Repository.TransactionRepository;
import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import exception.AccountNotFoundException;
import exception.InsufficentBalanceException;
import exception.ValidationException;
import util.Validation;

//In interface we define some predefined methods
public class BankServiceImpl implements BankService {

	private final AccountRepository accountRepository = new AccountRepository();
	private final TransactionRepository transactionReposioty = new TransactionRepository();
	private final CustomerRepository customerRepository = new CustomerRepository();
	private final Validation<String> validateName = name -> {
		if (name == null || name.isBlank())
			throw new ValidationException("Name is Required it cannot be null or empty");
	};

	private final Validation<String> validateEmail = email -> {
		if (email == null || ! email.contains("@"))
			throw new ValidationException("Email is Required and must contain @");
	};
	private final Validation<String> validateType = type -> {
		if (type== null || !type.equalsIgnoreCase("SAVINGS") || type.equalsIgnoreCase("CURRENT"))
			throw new ValidationException("Type is Required choose either savings or current");
	};
	private final Validation<Double> validateAmount = amount ->{
		if(amount == null || amount <0)
		{
			throw new ValidationException("Amount should be valid");
		}
	};

	@Override
	public String openAccount(String name, String email, String accountType) {
          validateName.validate(name);
          validateName.validate(email);
          validateName.validate(accountType);
          
		String accountNumber = getAccountNumber();
		String customerId = UUID.randomUUID().toString();
		Account account = new Account(accountNumber, customerId, 0.0, accountType);

		Customer customer = new Customer(name, customerId, email);
		customerRepository.save(customer);

		// Save Account

		accountRepository.save(account);
		return accountNumber;

	}

	public String getAccountNumber() {
		int temp = accountRepository.findAll().size() + 1;
		String accountNumber = String.format("AC%06d", temp);
		return accountNumber;
	}

	@Override
	public List<Account> listAccounts() {
		// TODO Auto-generated method stub

		return accountRepository.findAll().stream().sorted(Comparator.comparing(Account::getAccountNumber))
				.collect(Collectors.toList());
	}

	@Override
	public void deposit(String accountNumber, Double amount, String note) {
		// TODO Auto-generated method stub
		validateAmount.validate(amount);
		Account account = accountRepository.findByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("No Account found"));

		account.setBalance(account.getBalance() + amount);

		Transaction transcation = new Transaction(UUID.randomUUID().toString(), Type.DEPOSIT,
				account.getAccountNumber(), amount, LocalDateTime.now(), note);
		transactionReposioty.add(transcation);

	}

	@Override
	public void withdraw(String accountNumber, Double amount, String note) {
		// TODO Auto-generated method stub
		validateAmount.validate(amount);
		Account account = accountRepository.findByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException("No Account found"));

		if (account.getBalance().compareTo(amount) < 0) {
			throw new InsufficentBalanceException("Insufficent Balance");
		}
		account.setBalance(account.getBalance() - amount);
		Transaction transcation = new Transaction(UUID.randomUUID().toString(), Type.WITHDRAW,
				account.getAccountNumber(), amount, LocalDateTime.now(), note);
		transactionReposioty.add(transcation);

	}

	@Override
	public void transfer(String fromAccount, String toAccount, Double amount, String note) {
		// TODO Auto-generated method stub
		validateAmount.validate(amount);
		if (fromAccount.equals(toAccount)) {
			throw new ValidationException("From and TO account cannot be similar");
		}
		Account fromAcc = accountRepository.findByNumber(fromAccount)
				.orElseThrow(() -> new AccountNotFoundException("No account found"));
		Account toAcc = accountRepository.findByNumber(toAccount)
				.orElseThrow(() -> new AccountNotFoundException("No account found"));

		if (fromAcc.getBalance().compareTo(amount) < 0) {
			throw new InsufficentBalanceException("Insufficent Balance");
		}
		fromAcc.setBalance(fromAcc.getBalance() - amount);
		toAcc.setBalance(toAcc.getBalance() + amount);
		Transaction fromTranscation = new Transaction(UUID.randomUUID().toString(), Type.TRANSFER_Out,
				fromAcc.getAccountNumber(), amount, LocalDateTime.now(), note);
		transactionReposioty.add(fromTranscation);
		Transaction toTranscation = new Transaction(UUID.randomUUID().toString(), Type.TRANSFER_IN,
				toAcc.getAccountNumber(), amount, LocalDateTime.now(), note);
		transactionReposioty.add(toTranscation);
	}

	@Override
	public List<Transaction> getStatement(String accountNumber) {
		// TODO Auto-generated method stub
		return transactionReposioty.findByAccount(accountNumber).stream()
				.sorted(Comparator.comparing(Transaction::getTimestamp)).collect(Collectors.toList());

	}

	@Override
	public List<Account> findAccountByCustomerName(String q) {
		// TODO Auto-generated method stub
		String query = (q == null) ? " " : q.toLowerCase();
		List<Account> result = new ArrayList<>();
		for (Customer c : customerRepository.findAll()) {
			if (c.getName().toLowerCase().contains(query)) {
				result.addAll(accountRepository.findByCustomerId(c.getId()));
			}
		}
		result.sort(Comparator.comparing(Account::getAccountNumber));
		return result;

	}

}
