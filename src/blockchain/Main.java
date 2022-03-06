package blockchain;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Blockchain bc = new Blockchain();
		
		System.out.println("Generating block 1...");
		bc.addBlock(new Block(1, LocalDate.now(), "1200btc"));
		
		System.out.println("Generating block 2...");
		bc.addBlock(new Block(2, LocalDate.now(), "-1200btc"));
		
		System.out.println(bc);

 

	}
}
