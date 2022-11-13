package Default;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {
	
	private String firstName;
	private String lastName;
	private String idUser;
	private byte pinHash[];
	private ArrayList<Account> accounts; // lista de cuentas del usuario

	
	
/**
 * 
 * @param firstName
 * @param lastName
 * @param pin
 * @param theBank
 */
 	
	
	// constructor
	public User(String firstName, String lastName, String pin,Bank theBank) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		
    //	  hash pin user
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println("error, NoSuchAlgorithmException ");
			e.printStackTrace();
			System.exit(1);						
		}
		
		
		// new user id
		this.idUser = theBank.getNewUserId();
		
		
		// lista vacia de cuentas
		
		this.accounts = new ArrayList<Account>();
		
		System.out.printf("New User %s, %s with id %s created. \n", lastName, firstName, this.idUser);		
		
		
	}
	
	/**
	 * 
	 * @param anAccount
	 */
	
	public void addAccount(Account anAccount) {
		this.accounts.add(anAccount);
	}

	public String getIdUser() {
		return idUser;
	}

	
	/**
	 * 
	 * @param aPin
	 * @return
	 */
	public boolean validatePin(String aPin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash );
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block			
			System.err.println("error, NoSuchAlgorithmException ");
			e.printStackTrace();
			System.exit(1);	
		}
		return false;
		
	}

	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}
	
	
	public void printAccountsSummary() {
		System.out.printf(" \n\n %s's accounts summary", this.firstName);
		for(int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1,  this.accounts.get(a).getSummaryLine());
		}
		System.out.println();
	}
	
	public int numAccounts() {
		return this.accounts.size();
	}
	
	/**
	 * Print transaction history for a particular account
	 * @param accountIndex
	 */
	public void printAccountTransHistory (int accountIndex) {
		this.accounts.get(accountIndex).printTransHistory();
	}
	
	public double getAccountBalance( int accountIndex) {
		return this.accounts.get(accountIndex).getBalance();
	}
	
	/**
	 * Get the accountId of a particular account
	 * @param accountId
	 * @return
	 */
	public String getIdAccount(int accountIndex) {
		return this.accounts.get(accountIndex).getIdAccount();
	}

	/**
	 * 
	 * @param accountIndex
	 * @param amount
	 * @param memo
	 */
	public void addAccountTransaction(int accountIndex, double amount, String memo) {
		this.accounts.get(accountIndex).addTransaction(amount,memo);	
	}
	
}



