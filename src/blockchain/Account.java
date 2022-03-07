package blockchain;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class Account {
	private static final String ALGO_SIGN = "SHA256withRSA";
	private static final String ALGO_KEYS = "RSA";
	private String name;
	private KeyPair kp;

	public Account(String name) {
		KeyPairGenerator kpg = null;
		try {
			kpg = KeyPairGenerator.getInstance(ALGO_KEYS);
			kpg.initialize(2048);
			this.kp = kpg.generateKeyPair();
			this.name = name;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		return name;
	}

	protected Key getPrivate() {
		return kp.getPrivate();
	}
	
	protected Key getPublic() {
		return kp.getPublic();
	}
	
	protected String getPrivateDigest() {
		return 	CryptoToolBox.hashString(this.getPrivate().toString());
	}
	
	protected String getPublicDigest() {
		return 	CryptoToolBox.hashString(this.getPublic().toString());
	}
	
	
	protected byte[] getSignatureFromTransaction(String s) {
		Signature sig = null;
		byte[] res = null;
		try {
			sig = Signature.getInstance(ALGO_SIGN);
			sig.initSign((PrivateKey)this.getPrivate());
			sig.update(s.getBytes());
			res = sig.sign();
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public String toString() {
		return this.getPublicDigest();
	}
	
	
}
