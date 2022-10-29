package Default;

import java.util.ArrayList;

public class Account {
	
	private String name;	
	private String idAccount;
	private User holder; // relac con user.java
	private ArrayList<Transaction> transactions; // lista transacciones de la cuenta

	
	
 /**
  * 
  * @param name
  * @param holder
  * @param theBank
  */
	
	public Account(String name, User holder, Bank theBank) {
		this.name = name;
		this.holder = holder;
		
		
		// new account id
		this.idAccount = theBank.getNewAccountId();
		
		// init transactions
		this.transactions = new ArrayList<Transaction>();
		
	
		
	}



	public String getIdAccount() {
	// TODO Auto-generated method stub
	return idAccount;
    }
	
	
	 

	
	
	
}
