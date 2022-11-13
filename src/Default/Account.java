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
	
/**
 * 
 * @return String summary
 */
    public String getSummaryLine() {
	
	// get the account's balance
	double balance = this.getBalance();
	
	// format the summary line in case of negative balance
	
	if (balance >= 0) {
		return String.format("%s : $%.02f : %s",this.idAccount,balance, this.name);
	} else {
		return String.format("%s : ($%.02f) : %s",this.idAccount,balance, this.name);
	 }	
    }

    public double getBalance() {
    	double balance = 0;
    	for(Transaction transaction : this.transactions) {
    		balance += transaction.getAmount();
    	}
    	return balance;
    }
    
    public void printTransHistory() {
    	System.out.printf("\n Transaction history for account %s \n", this.idAccount);
    	for(int t = this.transactions.size() -1; t >= 0; t--) {
    		System.out.printf(this.transactions.get(t).getSummaryLine());
    	}
    	System.out.println();
    }
	
	public void addTransaction(double amount, String memo) {		
		Transaction newTransaction = new Transaction(amount, memo, this);
		this.transactions.add(newTransaction);
	}
	
	
}
