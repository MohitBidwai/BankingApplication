package service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import Repository.AccountRepository;
import domain.Account;

//In interface we define some predefined methods
public class BankServiceImpl implements BankService {

	private final AccountRepository accountRepository = new AccountRepository();

	@Override
	public String openAccount(String name, String email, String accountType) {

		String accountNumber = getAccountNumber();
		String customerId = UUID.randomUUID().toString();
		Account account = new Account(accountNumber, customerId, 0.0, accountType);

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
		Account account = accountRepository.findByNumber(accountNumber)
				.orElseThrow(() -> new RuntimeException("No Account found"));
		
		      account.setBalance(account.getBalance()+amount);
		

	}

}
