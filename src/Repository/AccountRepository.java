package Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import domain.Account;

public class AccountRepository {

	private final Map<String, Account> accountsByNumber = new HashMap<>();
	
	public void save(Account account)
	{
		accountsByNumber.put(account.getAccountNumber(), account);
	}

	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(accountsByNumber.values());
	}

	public Optional<Account> findByNumber(String accountNumber) {
		
		return Optional.ofNullable(accountsByNumber.get(accountNumber));
		// TODO Auto-generated method stub
		
	}

}
