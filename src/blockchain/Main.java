package blockchain;



public class Main {

	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		Account a1 = new Account("moha");
		Account a2 = new Account("foo");
		Transaction t = new Transaction(a1, a2, 10);
		t.signTransaction();
		

		try {
			bc.addTransaction(t);
			bc.minePendingTransactions(a1);
			bc.isValid();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(bc.getLatestBlock().hash);
		System.out.println(bc.blockchain.get(1).hash);
		System.out.println(bc.getLatestBlock().calculateHash());
		System.out.println(bc.blockchain.get(1).calculateHash());
	}

}
