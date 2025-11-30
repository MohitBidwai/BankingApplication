package Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Transaction;

public class TransactionRepository {
	private final Map<String, List<Transaction>> txByAccount = new HashMap<>();

	public void add(Transaction transcation) {

		List<Transaction> list = txByAccount.computeIfAbsent(transcation.getAccountNumber(), k -> new ArrayList<>());
		list.add(transcation);
		// TODO Auto-generated method stub

	}

	public List<Transaction> findByAccount(String accountNumber) {
		// TODO Auto-generated method stub

		return new ArrayList<>(txByAccount.getOrDefault(accountNumber, Collections.emptyList()));

	}

}
