package blockchain;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

public class Account {
	private static final String ALGO = "SHA256withRSA";
	private static KeyPairGenerator kpg;
	private KeyPair kp;

	public Account() {
		try {
			kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);
			KeyPair kp = kpg.generateKeyPair();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	protected Key getPrivate() {
		return this.kp.getPrivate();
	}
	
	protected Key getPublic() {
		return this.kp.getPublic();
	}
	
	protected String getSignatureFromTransaction(String s) {
		Signature sig = null;
		String res = null;
		try {
			sig = Signature.getInstance(ALGO);
			sig.initSign((PrivateKey)this.getPrivate());
			sig.update(s.getBytes());
			res = new String(sig.sign());
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
