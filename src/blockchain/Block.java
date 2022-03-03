package blockchain;


import java.time.LocalDate;
import java.util.Arrays;

public class Block {
	protected int index;
	protected LocalDate timeStamp;
	protected String data;
	protected String hash;
	protected String previousHash;
	protected int nonce;

	public Block(int index, LocalDate timeStamp, String data) {
		this.index = index;
		this.timeStamp = timeStamp;
		this.data = data;
		this.previousHash = "";
		this.hash = this.calculateHash();
		this.nonce = 0;
	}

	public String calculateHash() {
		String text = index+timeStamp.toString()+data.toString()+previousHash.toString()+this.nonce;
		return CryptoToolBox.hashString(text);

	}
	
	void mineBlock(int difficulty){
		String zeros = "";
		for (int i = 0; i < difficulty; i++) {
			zeros+="0";
			
		}
		while (!this.hash.substring(0, difficulty).equals(zeros)) {
			this.nonce++;
			this.hash = this.calculateHash();
		}
		
		System.out.println("Success: "+this.hash);
	}
	
	@Override
	public String toString() {
		return timeStamp.toString()+":"+index+":"+data+":"+hash+":"+previousHash;
	}
}


