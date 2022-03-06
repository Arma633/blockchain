package blockchain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Blockchain {
	ArrayList<Block> blockchain;
	private static final int DIFFICULTY = 5;

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
		b.mineBlock(DIFFICULTY);
		this.blockchain.add(b);
	}

	public boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		for (int i = 1; i < this.blockchain.size(); i++) {
			currentBlock = this.blockchain.get(i);
			previousBlock = this.blockchain.get(i-1);
			
			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				return false;
			}
			
			if (!currentBlock.previousHash.equals(previousBlock.hash)) {
				return false;
			}
		}
		return true;
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
