package blockchain;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		bc.addBlock(new Block(1, LocalDate.now(), "amount 2"));
		bc.addBlock(new Block(2, LocalDate.now(), "amount 1"));
		
		System.out.println(bc.toString());
		
	}
}
