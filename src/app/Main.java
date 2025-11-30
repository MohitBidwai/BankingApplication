package app;

import java.util.List;
import java.util.Scanner;

import domain.Account;
import service.BankService;
import service.BankServiceImpl;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		boolean running = true;
		BankService bankService = new BankServiceImpl();
		System.out.println("Welcome to the Console Bank");
		while (running) {
			System.out.println("""
					1) Open Account
					2) Deposit
					3) Withdraw
					4) Transfer
					5) Account Statement
					6) List Accounts
					7) Search Accounts by Customer Name
					0) Exit
					""");
			System.out.println("Choose an Option of your choice");
			int option = scanner.nextInt();
			scanner.nextLine();
			System.out.println("CHOICE :" + option);
			switch (option) {
			case 0:
				running = false;
				break;
			case 1:
				openAccount(scanner, bankService);
				break;
			case 2:
				deposit(scanner, bankService);
				break;
			case 3:
				withdraw(scanner, bankService);
				break;
			case 4:
				transfer(scanner, bankService);
				break;
			case 5:
				statement(scanner, bankService);
				break;

			case 6:
				listAccount(scanner, bankService);
				break;
			case 7:
				searchAccounts(scanner,bankService);
				break;
			}
		}

	}

	private static void openAccount(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		System.out.println("Enter you Name: ");
		String name = scanner.nextLine().trim();
		System.out.println("Enter your email: ");
		String email = scanner.nextLine().trim();
		System.out.println("Account Type (SAVINGS/CURRENT): ");
		String type = scanner.nextLine().trim();
		System.out.println("Intial deposit");
		String amountStr = scanner.nextLine().trim();
		String accountNumber = bankService.openAccount(name, email, type);
		Double initial = Double.valueOf(amountStr);
		if (initial > 0) {
			bankService.deposit(accountNumber, initial, "Deposit");
		}
		bankService.openAccount(name, email, type);
		System.out.println("Account opened :" + accountNumber);

	}

	private static void deposit(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub

		System.out.println("Enter the Accout number :");
		String accountNumber = scanner.nextLine().trim();
		System.out.println("Enter the Amount you want to deposit");
		Double amount = scanner.nextDouble();
		bankService.deposit(accountNumber, amount, "Deposited");
		System.out.println("Amount has been deposited to your account");

	}

	private static void withdraw(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Accout number :");
		String accountNumber = scanner.nextLine().trim();
		System.out.println("Enter the Amount you want to withdraw");
		Double amount = scanner.nextDouble();
		bankService.withdraw(accountNumber, amount, "Withdraw");
		System.out.println("Amount has been Withdrawn from your account");
	}

	private static void transfer(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Accout from which you want to transfer money :");
		String fromAccount = scanner.nextLine().trim();
		System.out.println("Enter the Accout to which you want to transfer money :");
		String toAccount = scanner.nextLine().trim();
		System.out.println("Enter the amount you want to transfer:");
		Double amount = scanner.nextDouble();
		bankService.transfer(fromAccount, toAccount, amount, "Transfer");
		System.out.println("Amount has been transfered successfully");

	}

	private static void statement(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		System.out.println("Enter the Accout Number :");
		String accountNumber = scanner.nextLine().trim();
		bankService.getStatement(accountNumber).
		forEach(t -> {
		    System.out.println(
		        t.getTimestamp() + " | " +
		        t.getNote() + " | " +
		        t.getType()
		    );
		});


	}

	private static void listAccount(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		bankService.listAccounts().forEach(
				a -> System.out.println(a.getAccountNumber() + " | " + a.getAccountType() + "|" + a.getBalance()));

	}

	private static void searchAccounts(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		System.out.println("Enter the CustomerName:  ");
		String customerName = scanner.nextLine().trim();
		bankService.findAccountByCustomerName(customerName).forEach(account->System.out.println(account.getAccountNumber() + "|" + account.getAccountType()));

	}
}
