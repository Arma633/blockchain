package blockchain;

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
	
	@Override
	public String toString() {
		return "from:"+fromAddr+"\nto:"+toAddr+"\namount:"+amt+"\n";
	}
	

}
