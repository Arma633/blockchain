package blockchain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Transaction {
	private static final String ALGO_SIGN = "SHA256withRSA";
	protected Account fromAddr;
	protected Account toAddr;
	protected int amt;
	protected byte[] signature;


	public Transaction(Account fromAddr, Account toAddr, int amt) {
		this.fromAddr = fromAddr;
		this.toAddr = toAddr;
		this.amt = amt;
	}

	protected String calculateTransHash() {
		return CryptoToolBox.hashString(fromAddr.toString()+toAddr.toString()+amt);
	}

	protected void signTransaction() {
		String hashTrans = this.calculateTransHash();
		byte[] sig = fromAddr.getSignatureFromTransaction(hashTrans);
		this.signature = sig;

	}

	protected boolean isValid() {
		if(this.fromAddr.equals(Blockchain.REWARDER_ADDR)) {
			return true;
		}

		if(this.signature == null || this.signature.length == 0 ) {
			return false;
		}

		Signature sign;
		try {
			sign = Signature.getInstance(ALGO_SIGN);
			sign.initVerify((PublicKey) fromAddr.getPublic());
			sign.update(this.calculateTransHash().getBytes());
			return sign.verify(this.signature);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;





	}

	@Override
	public String toString() {
		return "from:"+fromAddr+"\nto:"+toAddr+"\namount:"+amt+"\n";
	}


}
