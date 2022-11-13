package Default;

import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// init scanner
		Scanner sc = new Scanner(System.in);

		// init bank
		Bank theBank = new Bank("Bank of Example");

		// add user
		User user = theBank.addUser("Alberto", "Fernandez", "1234");

		// checking account
		Account newAccount = new Account("Checking", user, theBank);
		user.addAccount(newAccount);
		theBank.addAccount(newAccount);

		User curUser;
		while (true) {

			// stay in the login prompt until successful login
			curUser = ATM.mainMenuPrompt(theBank, sc);

			// stay in the menu until user quits
			ATM.printUserMenu(curUser, sc);

		}

	}

	/**
	 * 
	 * @param theBank
	 * @param sc
	 * @return
	 */
	public static User mainMenuPrompt(Bank theBank, Scanner sc) {

		String idUser;
		String pin;
		User authUser;

		// prompt the user for user id/pin
		do {
			System.out.printf("\n \n Welcome to %s \n\n", theBank.getName());
			System.out.println("Enter user ID: ");
			idUser = sc.nextLine();
			System.out.println("Please enter your pin: ");
			pin = sc.nextLine();

			// get user object
			authUser = theBank.userLogin(idUser, pin);

			if (authUser == null) {
				System.out.println("Incorrect Id/Pin combination. " + "Please try again");
			}

		} while (authUser == null);

		return authUser;

	}

	public static void printUserMenu(User user, Scanner sc) {
		
		// print summary of user's account
		user.printAccountsSummary();
			
		int choice;
		
		// user menu
		do {
			System.out.printf("Welcome %s", "Please, choose an option", 
					user.getFirstName());
			System.out.println(" 1. Show account transaction history");
			System.out.println(" 2. Withdraw");
			System.out.println(" 3. Deposit");
			System.out.println(" 4. Transfer");
			System.out.println(" 5. Quit");
			System.out.println();
			System.out.println("Enter choice: ");
			choice = sc.nextInt();
			
		
			if(choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose a correct option");
			    }	
			} while(choice < 1 || choice > 5);

			
			// process choice
			
			  switch(choice) {
			  
			  case 1: 
				  ATM.showTransHistory(user, sc);
				  break;
			  case 2:
				  ATM.withdrawFunds(user, sc);
				  break;
			  case 3:
				  ATM.depositFunds(user, sc);
				  break;
			  case 4:
				  ATM.transferFunds(user, sc);
				  break;

			  }
			  
			  
			  // redisplay the menu until user quits
			  // call printMenu from inside printMenu
			  
			  if(choice != 5) {
				  ATM.printUserMenu(user, sc);
			  }
		}
	
		
	/**
	 * Show transaction history for an account
	 * @param theUser  the logged-in User object
	 * @param sc scanner object used for user input
	 */
	public static void showTransHistory(User theUser, Scanner sc) {
		
		int theAccount;
		
		// get the account > transaction history
		do {
			System.out.printf("Enter the number (1-%d of the account whose transactions"
					+ "you want to see", theUser.numAccounts());
			
			theAccount = sc.nextInt()-1;
			
			if(theAccount < 0 || theAccount >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please insert a correct one");
		    	}
			
		}while( theAccount < 0 || theAccount >= theUser.numAccounts());
		
		
		theUser.printAccountTransHistory(theAccount);
	}
	
	
	
	/**
	 * 
	 * @param theUser
	 * @param sc
	 */
	public static void transferFunds(User theUser, Scanner sc) {
		
		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;
		
		// transfer account
		do {
			System.out.printf("Enter the number (1-%d of the account\n" + 
		"to transfer from: ");
		fromAccount = sc.nextInt()-1;
		if(fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again");
		  }
		} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
		accountBalance = theUser.getAccountBalance(fromAccount);
		
		
		// receiver account
		do {
			System.out.printf("Enter the number (1-%d of the account\n" + 
		"to transfer to: ");
		toAccount = sc.nextInt()-1;
		if(toAccount < 0 || toAccount >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again");
		  }
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());	
		
		
		// transfer amount 
		do {
			System.out.printf("Enter the amount to transfer(max $%,02f): $", accountBalance);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Please insert a correct amount to transfer");
			} else if (amount > accountBalance){
				System.out.println("There is no enough money to transfer that amount");				
			} 			
		} while(amount < 0 || amount > accountBalance);
		
		// transfer
		
		theUser.addAccountTransaction(fromAccount, -1 * amount, String.format(
				"Transfer to account %s", theUser.getIdAccount(toAccount)));
		theUser.addAccountTransaction(toAccount, amount, String.format(
				"Transfer to account %s", theUser.getIdAccount(fromAccount)));
		
				
	}
	
	
	
	/**
	 * 
	 * @param theUser
	 * @param sc
	 */
	public static void withdrawFunds(User theUser, Scanner sc) {
		
		int fromAccount;		
		double amount;
		double accountBalance;
		String memo;
		
		
		
		// transfer account
		do {
			System.out.printf("Enter the number (1-%d of the account\n" + 
		"to transfer from: ");
		fromAccount = sc.nextInt()-1;
		if(fromAccount < 0 || fromAccount >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again");
		  }
		} while(fromAccount < 0 || fromAccount >= theUser.numAccounts());
		accountBalance = theUser.getAccountBalance(fromAccount);
		
		
		// transfer amount 
		do {
			System.out.printf("Enter the amount to transfer(max $%,02f): $", accountBalance);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Please insert a correct amount to transfer");
			} else if (amount > accountBalance){
				System.out.println("There is no enough money to transfer that amount");				
			} 			
		} while(amount < 0 || amount > accountBalance);
		
		
		
		// check previous input
		sc.nextLine();
		
		
		// get a memo
		System.out.println("Please enter a memo: ");
		memo = sc.nextLine();
		
		
		// withdraw
		
		theUser.addAccountTransaction(fromAccount, -1*amount, memo);
				
	}
	
	
	
	public static void depositFunds(User theUser, Scanner sc) {
		
		int toAccount;		
		double amount;
		double accountBalance;
		String memo;	
		
		
		// transfer account
		do {
			System.out.printf("Enter the number (1-%d of the account\n" + 
		"to transfer from: ");
			toAccount = sc.nextInt()-1;
		if(toAccount < 0 || toAccount >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again");
		  }
		} while(toAccount < 0 || toAccount >= theUser.numAccounts());
		accountBalance = theUser.getAccountBalance(toAccount);
		
		
		// transfer amount 
		do {
			System.out.printf("Enter the amount to transfer(max $%,02f): $", accountBalance);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Please insert a correct amount to transfer");
			} 			
		} while(amount < 0 || amount > accountBalance);
		
				
		// check previous input
		sc.nextLine();
		
		
		// get a memo
		System.out.println("Please enter a memo: ");
		memo = sc.nextLine();
		
		
		// deposit
		
		theUser.addAccountTransaction(toAccount, amount, memo);	
		
	}
	
	
}


