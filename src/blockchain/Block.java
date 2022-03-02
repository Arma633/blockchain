package blockchain;


import java.time.LocalDate;

public class Block {
	protected int index;
	protected LocalDate timeStamp;
	protected String data;
	protected String hash;
	protected String previousHash;

	public Block(int index, LocalDate timeStamp, String data) {
		this.index = index;
		this.timeStamp = timeStamp;
		this.data = data;
		this.previousHash = "";
		this.hash = this.calculateHash();
	}

	String calculateHash() {
		String text = index+timeStamp.toString()+data.toString()+previousHash.toString();
		return CryptoToolBox.hashString(text);

	}
	
	@Override
	public String toString() {
		return timeStamp.toString()+":"+index+":"+data+":"+hash+":"+previousHash;
	}
}


