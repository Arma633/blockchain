package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoToolBox {
	private static final String METHOD = "SHA-256";

	public static String hashString(String s) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(METHOD);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hash);
		
	}
}
