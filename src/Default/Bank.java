package Default;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	private String name;
	private ArrayList<User> users; // lista de usuarios del banco
	private ArrayList<Account> accounts; // lista de cuentas del banco que debe coincidir con usuarios
	
	
	
/**
 * Constructor with empty lists
 * @param name
 */
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
		
	}
	
	
	
	
	
	/**
	 * Generate new id for user
	 * @return idUser
	 */	
	public String getNewUserId() {
		
		// random number
		String idUser;
		Random rng = new Random();
		int length = 6;
		boolean nonUnique;
		
		
		do {
			
			// generate number
			idUser = "";
			for(int i = 0; i<length; i++) {
				idUser += ((Integer)rng.nextInt(10)).toString();
			}
			
			// check uniqueness
			nonUnique = false;
			for(User user : this.users) {
				if(idUser.compareTo(user.getIdUser()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while(nonUnique);
		
		return idUser;	
	
	};
	
	
	/**
	 * Generate new id for account
	 * @return idAccount
	 */
	public String getNewAccountId() {
		
		// random number
				String idAccount;
				Random rng = new Random();
				int length = 6;
				boolean nonUnique;
				
				
				do {
					
					// generate number
					idAccount = "";
					for(int i = 0; i<length; i++) {
						idAccount += ((Integer)rng.nextInt(10)).toString();
					}
					
					// check uniqueness
					nonUnique = false;
					for(Account account : this.accounts) {
						if(idAccount.compareTo(account.getIdAccount()) == 0) {
							nonUnique = true;
							break;
						}
					}
				} while(nonUnique);
				
				return idAccount;
	};
	
	
	/**
	 * 
	 * @param anAccount
	 */	
	public void addAccount(Account anAccount) {
		this.accounts.add(anAccount);
	}
	
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param pin
	 * @return
	 */	
	public User addUser (String firstName, String lastName, String pin) {
		
		User newUser = new User(firstName, lastName,pin,this);
		this.users.add(newUser);
		
		// create a savings for the new user
		
		Account newAccount = new Account("Savings", newUser, this);
		
		// add itself into holder and bank
		// same object in each list(not a copy)		
		
		newUser.addAccount(newAccount);
		this.addAccount(newAccount); 
		 
		return newUser;		
	}
	
	/**
	 * User object associated with a Id and pin if they are valid
	 * @param idUser
	 * @param pin
	 * @return
	 */
	public User userLogin (String idUser, String pin) {
		for (User user: this.users) {
			if(user.getIdUser().compareTo(idUser) == 0 && user.validatePin(pin)) {
				return user;
			}
		}
		// if no user is found	or the pin is incorrect	
		return null;
	}





	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	} 
	
	
	
	

}
