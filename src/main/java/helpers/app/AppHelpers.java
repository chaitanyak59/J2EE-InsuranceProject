package helpers.app;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class AppHelpers {
	
	private static final int ADMIN_ROLE_ID = 1;
	
	/*Uses Salt and Password to create Hash*/
    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    public static byte[] getSalt() {
    	byte[] salt = new byte[20];
    	try {
    		SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            return salt;
    	}catch (Exception e) {
            e.printStackTrace();
        }
    	return salt;
    }
    
    /*Convert Bytes To String*/
    public static String convertBtoString(byte[] bytes) {
    	return new String(bytes); 
    }
    
    public static boolean isAdmin(int role) {
    	return role == ADMIN_ROLE_ID;
    }
}
