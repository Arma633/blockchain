package blockchain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Blockchain {
	ArrayList<Block> blockchain;

	public Blockchain() {
		this.blockchain = new ArrayList<Block>();
		this.genesis();
	}
	
	private void genesis() {
		Block genesis = new Block(0,LocalDate.now(),"Genesis");
		genesis.previousHash = "0";
		this.blockchain.add(genesis);
		
	}
	
	public Block getLatestBlock() {
		return this.blockchain.get(this.blockchain.size()-1);
	}
	
	public void addBlock(Block b) {
		b.previousHash = this.getLatestBlock().hash;
		b.hash = b.calculateHash();
		this.blockchain.add(b);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Block block : blockchain) {
			sb.append(block+"\n");
		}
		return sb.toString();
	}
}
