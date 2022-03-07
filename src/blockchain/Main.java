package blockchain;



public class Main {
	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		Account userAccount = new Account("user-account");
		Account otherAccount = new Account("other-account");
		
		Transaction tx1 = new Transaction(userAccount, otherAccount, 10);
		tx1.signTransaction();
		try {
			bc.addTransaction(tx1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bc.minePendingTransactions(userAccount);
		

		
	}
}
