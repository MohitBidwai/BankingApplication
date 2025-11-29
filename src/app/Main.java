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
				deposit(scanner,bankService);
				break;
			case 3:
				withdraw(scanner);
				break;
			case 4:
				transfer(scanner);
				break;
			case 5:
				statement(scanner);
				break;

			case 6:
				listAccount(scanner, bankService);
				break;
			case 7:
				searchAccount(scanner);
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
		Double intial = Double.valueOf(amountStr);
		bankService.openAccount(name, email, type);

	}

	private static void deposit(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		
    
    
    System.out.println("Enter the Accout number :");
    String accountNumber = scanner.nextLine().trim();
    System.out.println("Enter the Amount you want to deposit");
    Double amount = scanner.nextDouble();
    bankService.deposit(accountNumber,amount,"Deposited");
    System.out.println("Amount has been deposited to your account");
    
	}

	private static void withdraw(Scanner scanner) {
		// TODO Auto-generated method stub

	}

	private static void transfer(Scanner scanner) {
		// TODO Auto-generated method stub

	}

	private static void statement(Scanner scanner) {
		// TODO Auto-generated method stub

	}

	private static void listAccount(Scanner scanner, BankService bankService) {
		// TODO Auto-generated method stub
		bankService.listAccounts().forEach(
				a -> System.out.println(a.getAccountNumber() + " | " + a.getAccountType() + "|"  + a.getBalance()));

	}

	private static void searchAccount(Scanner scanner) {
		// TODO Auto-generated method stub

	}
}
