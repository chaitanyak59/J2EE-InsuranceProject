package users.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sqlQuery = "INSERT INTO users(name,email,hash,salt,mobile,address, role_id) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getHash());
			pstmt.setBytes(4, user.getSalt());
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
			String sqlQuery = "SELECT email,hash,salt,name,role_id,id from users where email=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, email);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()){  
				user.setEmail(rs.getString(1));
				user.setHash(rs.getString(2));
				user.setSalt(rs.getBytes(3));
				user.setName(rs.getString(4));
				user.setRole_id(rs.getInt(5));
				user.setId(rs.getInt(6));
			}  
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		}
		return user;
    }
    
    public List<Users> getAllUsers(String searchParam) {
    	String searchQuery;
    	PreparedStatement pstmt;
		List<Users> users = new ArrayList<Users>();
		try {
			if(searchParam== null || (searchParam.length() == 0)) {
	    		searchQuery = "SELECT * FROM users;";
				pstmt = (PreparedStatement) connection.prepareStatement(searchQuery);
	    	} else {
	    		searchQuery = "SELECT * FROM users where name=? OR address=? OR email=?";
				pstmt = (PreparedStatement) connection.prepareStatement(searchQuery);
				pstmt.setString(1, searchParam);
				pstmt.setString(2, searchParam);
				pstmt.setString(3, searchParam);
	    	}
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				 Users user = new Users();      
				 user.setId(rs.getInt("id"));
				 user.setName(rs.getString("name"));
				 user.setEmail(rs.getString("email"));
				 user.setMobileNo(rs.getString("mobile"));
				 user.setIsCreatedAt(rs.getDate("is_created_at"));
				 users.add(user);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Users>(); //Empty Array
		}
		return users;
	}
}
