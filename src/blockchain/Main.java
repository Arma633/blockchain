package blockchain;



public class Main {
	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		Account userAccount = new Account("Mohamed");
		Account otherAccount = new Account("Foo");
		
		System.out.println("Welcome " + userAccount.getName());
		System.out.println("Your public key is : "+ userAccount);
		System.out.println();
		
		Transaction tx1 = new Transaction(userAccount, otherAccount, 10);
		
		System.out.println("-----BEGIN TRANSACTION-----");
		System.out.print(tx1);
		System.out.println("-----END TRANSACTION-----");
		System.out.println();
		
		System.out.println("Signing transaction with public key...");
		tx1.signTransaction();
		String sigDigest = CryptoToolBox.hashString(new String(tx1.signature));
		
		System.out.println("Transaction: "+tx1.calculateTransHash() +" has signature : "+ sigDigest);
		System.out.println();
		
		
		try {
			System.out.println("Checking transactions validity then mining...");
			bc.addTransaction(tx1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bc.minePendingTransactions(userAccount);
		System.out.println(bc.getBalanceOfAddr(userAccount.getPublicDigest()));
		bc.minePendingTransactions(userAccount);
		System.out.println(bc.getBalanceOfAddr(userAccount.getPublicDigest()));
	}
}
