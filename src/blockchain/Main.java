package blockchain;



public class Main {
	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		bc.createTransaction(new Transaction("ShinZzz_Addr", "Foo_Addr",10));
		bc.createTransaction(new Transaction("ShinZzz_Addr", "Foo_Addr",10));
		bc.createTransaction(new Transaction("ShinZzz_Addr", "Foo_Addr",10));
		bc.viewPending();
		
		System.out.println("Mining...");
		bc.minePendingTransactions("Bar_Addr");
		System.out.println("Miner bal is : "+bc.getBalanceOfAddr("Bar_Addr")+"\n");
		
		bc.createTransaction(new Transaction("ShinZzz_Addr", "Foo_Addr",10));
		bc.viewPending();
		
		System.out.println("Mining...");
		bc.minePendingTransactions("Bar_Addr");
		System.out.println("Miner bal is : "+bc.getBalanceOfAddr("Bar_Addr"));
	}
}
