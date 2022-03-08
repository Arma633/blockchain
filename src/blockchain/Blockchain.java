package blockchain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Blockchain {
	private static final int MINING_REWARD = 10;
	private static final int DIFFICULTY = 2;
	protected static final Account REWARDER_ADDR = new Account("rewarder_addr");


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
		genesis.hash = genesis.calculateHash();
		this.blockchain.add(genesis);

	}

	protected Block getLatestBlock() {
		return this.blockchain.get(this.blockchain.size()-1);
	}

//	@Deprecated
//	private void addBlock(Block b) {
//		b.previousHash = this.getLatestBlock().hash;
//		b.mineBlock(DIFFICULTY);
//		this.blockchain.add(b);
//	}

	protected void minePendingTransactions(Account rewardToAddr) {
		Block b = new Block(LocalDate.now(), new ArrayList<Transaction>(this.pendingTransactions));
		b.previousHash = this.getLatestBlock().hash;
		b.hash = b.calculateHash();
		b.mineBlock(DIFFICULTY);
		this.blockchain.add(b);
		this.pendingTransactions.clear();
		try {
			this.addTransaction((new Transaction(REWARDER_ADDR, rewardToAddr, MINING_REWARD)));
		} catch (Exception e) {
			e.printStackTrace();
		};
	}

	protected boolean isValid() {
		Block currentBlock;
		Block previousBlock;
		for (int i = 1; i < this.blockchain.size(); i++) {
			currentBlock = this.blockchain.get(i);
			previousBlock = this.blockchain.get(i-1);

			if(!currentBlock.hasValidTransactions()) {
				return false;
			}

			if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
				return false;
			}

			if (!currentBlock.previousHash.equals(previousBlock.hash)) {
				return false;
			}
		}
		return true;
	}

	protected void addTransaction(Transaction t) throws Exception {

		if(t.fromAddr == null || t.toAddr == null) {
			throw new Exception("Error trans must include from and to addr");
		}

		if(!t.isValid()) {
			throw new Exception("Transaction not valid : " );
		}

		this.pendingTransactions.add(t);
	}

	protected int getBalanceOfAddr(String addr) {
		int balance = 0;
		for (Block block : blockchain) {
			for (Transaction t : block.transactions) {
				if(t.fromAddr.getPublicDigest().equals(addr)) {
					balance -= t.amt;
				}

				if(t.toAddr.getPublicDigest().equals(addr)) {
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
