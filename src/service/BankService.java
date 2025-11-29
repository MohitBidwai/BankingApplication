package service;

import java.util.List;

import domain.Account;

public interface BankService {

	public String openAccount(String name , String email , String accountType);
	public List<Account>listAccounts();
	public void deposit(String accountNumber, Double amount, String note);
}
