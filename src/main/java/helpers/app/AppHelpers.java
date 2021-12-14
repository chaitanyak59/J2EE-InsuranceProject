package helpers.app;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static java.util.Calendar.YEAR;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.DATE;

public class AppHelpers {
	public static final String USER_ID = "uid";
	public static final String USER_ROLE = "role";
	public static final String USER_NAME = "user";
	
	public static final int INVALID_PRODUCT = -1;
	public static final int ADMIN_ROLE_ID = 1;
	
	/*Uses Salt and Password to create Hash*/
    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
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
    
    public static Optional<String> getCookie(HttpServletRequest request, String key) {
    	return Arrays.stream(request.getCookies())
    		      .filter(c -> key.equals(c.getName()))
    		      .map(Cookie::getValue)
    		      .findAny();
    }
    
    public static Cookie createCookie(String key, String value, boolean isSecure, boolean isHttp, int age, String domain, String path) {
    	Cookie c1=new Cookie(key,value);
    	c1.setSecure(isSecure);
    	c1.setHttpOnly(isHttp);
    	c1.setMaxAge(age);
    	if(domain != null)  {
    		c1.setDomain(domain);
    	}
    	if(path != null) {
    		c1.setPath(path);
    	}
    	return c1;
    }
    
    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) || 
            (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
