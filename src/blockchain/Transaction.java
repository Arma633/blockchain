package blockchain;

import java.security.KeyPair;

public class Transaction {
	protected String fromAddr;
	protected String toAddr;
	protected int amt;
	
	
	public Transaction(String fromAddr, String toAddr, int amt) {
		super();
		this.fromAddr = fromAddr;
		this.toAddr = toAddr;
		this.amt = amt;
	}
	
	protected String calculateTransHash() {
		return CryptoToolBox.hashString(fromAddr+toAddr+amt);
	}
	
	protected void signTransaction(Account account) {
		String hashTrans = this.calculateTransHash();
		String sig = account.getSignatureFromTransaction(hashTrans);
		
		
	}
	
	@Override
	public String toString() {
		return "from:"+fromAddr+"\nto:"+toAddr+"\namount:"+amt+"\n";
	}
	

}
