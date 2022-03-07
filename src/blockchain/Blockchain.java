package blockchain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Blockchain {
	private static final int MINING_REWARD = 10;
	private static final int DIFFICULTY = 10;
	private static final String REWARDER_ADDR = "blockchain_system";


	ArrayList<Block> blockchain;
	ArrayList<Transaction> pendingTransactions;


	public Blockchain() {
		this.blockchain = new ArrayList<Block>();
		this.pendingTransactions = new ArrayList<>();
		this.genesis();
	}

	private void genesis() {
		Block genesis = new Block(LocalDate.now(),new ArrayList<Transaction>());
		genesis.previousHash = "0";
		this.blockchain.add(genesis);

	}

	protected Block getLatestBlock() {
		return this.blockchain.get(this.blockchain.size()-1);
	}

	@Deprecated
	private void addBlock(Block b) {
		b.previousHash = this.getLatestBlock().hash;
		b.mineBlock(DIFFICULTY);
		this.blockchain.add(b);
	}

	protected void minePendingTransactions(String rewardToAddr) {
		Block b = new Block(LocalDate.now(), this.pendingTransactions);
		b.mineBlock(DIFFICULTY);
		this.blockchain.add(b);
		this.pendingTransactions.clear();
		this.createTransaction((new Transaction(REWARDER_ADDR, rewardToAddr, MINING_REWARD)));;
	}

	protected boolean isValid() {
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

	protected void createTransaction(Transaction t) {
		this.pendingTransactions.add(t);
	}

	protected int getBalanceOfAddr(String addr) {
		int balance = 0;
		for (Block block : blockchain) {
			for (Transaction t : block.transactions) {
				if(t.fromAddr.equals(addr)) {
					balance -= t.amt;
				}

				if(t.toAddr.equals(addr)) {
					balance += t.amt;
				}

			}
		}
		return balance;
	}

	protected void viewPending() {
		for (Transaction t : pendingTransactions) {
			System.out.println(t);
		}
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
