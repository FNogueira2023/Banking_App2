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
	}

}
