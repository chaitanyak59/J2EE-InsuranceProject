package users.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import database.Database;
import users.model.Users;

public class UsersDao {
	private static UsersDao instance;
			
	private Connection connection;
	public UsersDao() {
		connection = Database.getConnection();
	}
	
	//SingleTon
	public static UsersDao getUsersDaoInstance() {
		if(instance == null) {
			return new UsersDao();
		}
		return instance;
	}
	
	public int createUserAccount(Users user) {
		String sqlQuery = "INSERT INTO users(name,email,hash,salt,mobile,address) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getHash());
			pstmt.setString(4, user.getSalt());
			pstmt.setString(5, user.getMobileNo());
			pstmt.setString(6, user.getAddress());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
    public boolean isUserExists(String email) {
    	System.out.println("Email:"+ email);
    	boolean userExists = false;
		try{
			String sqlQuery = "SELECT email from users where email=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, email);
			ResultSet rs= pstmt.executeQuery();
			if(rs.isBeforeFirst()) {
				userExists = true;
			} 
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		}
		return userExists;
    }
	
    public Users getUserLoginDetails(String email) {
    	Users user = new Users();
		try{
			String sqlQuery = "SELECT email,hash,salt,name,role_id from users where email=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, email);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){  
				user.setEmail(rs.getString(1));
				user.setHash(rs.getString(2));
				user.setSalt(rs.getString(3));
				user.setName(rs.getString(4));
				user.setRole_id(rs.getInt(5));
			}  
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		}
		return user;
    }
	
}
