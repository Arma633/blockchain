package blockchain;


import java.time.LocalDate;
import java.util.ArrayList;

public class Block {
	protected LocalDate timeStamp;
	protected ArrayList<Transaction> transactions;
	protected String hash;
	protected String previousHash;
	protected int nonce;

	public Block(LocalDate timeStamp, ArrayList<Transaction> transactions) {

		this.timeStamp = timeStamp;
		this.transactions = transactions;
		this.previousHash = "";
		this.hash = this.calculateHash();
		this.nonce = 0;
	}

	protected String calculateHash() {
		String text = timeStamp.toString()+transactions.toString()+previousHash.toString()+this.nonce;
		return CryptoToolBox.hashString(text);

	}
	
	protected void mineBlock(int difficulty){
		long initTime = System.currentTimeMillis();
		String zeros = "";
		for (int i = 0; i < difficulty; i++) {
			zeros+="0";
			
		}
		while (!this.hash.substring(0, difficulty).equals(zeros)) {
			this.nonce++;
			this.hash = this.calculateHash();
		}
		
		System.out.println("Success: "+this.hash + " in : "+ (System.currentTimeMillis()-initTime) +" miliseconds");
	}
	
	@Override
	public String toString() {
		return timeStamp.toString()+":"+transactions+":"+hash+":"+previousHash;
	}
}


