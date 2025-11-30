package service;

import java.util.List;

import domain.Account;
import domain.Transaction;

public interface BankService {

	public String openAccount(String name , String email , String accountType);
	public List<Account>listAccounts();
	public void deposit(String accountNumber, Double amount, String note);
	public void withdraw(String accountNumber, Double amount, String string);
	public void transfer(String fromAccount, String toAccount, Double amount, String string);
	public List<Transaction> getStatement(String accountNumber);
	public List<Account> findAccountByCustomerName(String q);
}
