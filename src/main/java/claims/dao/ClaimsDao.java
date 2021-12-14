package claims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import claims.model.Claims;
import database.Database;

public class ClaimsDao {
	private static ClaimsDao instance;
	private Connection connection;

	public ClaimsDao() {
		connection = Database.getConnection();
	}

	// SingleTon
	public static ClaimsDao getClaimsDaoInstance() {
		if (instance == null) {
			return new ClaimsDao();
		}
		return instance;
	}
	
	public int createClaimRequest(Claims claimReq) {
		String sqlQuery = "INSERT INTO claims(registration_id, category,description) VALUES(?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, claimReq.getRegistrationID());
			pstmt.setString(2, claimReq.getCategory());
			pstmt.setString(3, claimReq.getDescription());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public List<Claims> getAllClaims(String searchParam) {
		String sqlQuery = String.format("select c.*,p.name,u.email FROM claims c\r\n"
				+ "JOIN registrations rg ON rg.id = c.registration_id\r\n"
				+ "JOIN products p ON rg.product_id = p.id\r\n"
				+ "JOIN users u ON u.id = rg.user_id");
		List<Claims> claims =new ArrayList<Claims>();
		
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				Claims claim = new Claims();      
				 claim.setId(rs.getInt("id"));
				 claim.setUserEmail(rs.getString("email"));
				 claim.setProductName(rs.getString("name"));
				 claim.setClaimStatus(rs.getBoolean("claim_status"));
				 claim.setLastModifiedDate(rs.getDate("last_modified_at"));
				 claim.setDescription(rs.getString("description"));
				 claim.setCategory(rs.getString("category"));
				 claims.add(claim);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Claims>(); //Empty Array
		}
		return claims;
	}
	
	public List<Claims> getUserClaims(int userID, String searchParam) {
		String userQuery = String.format("select c.*,p.name FROM claims c\r\n"
				+ "JOIN registrations rg ON rg.id = c.registration_id\r\n"
				+ "JOIN products p ON rg.product_id = p.id\r\n"
				+ "JOIN users u ON u.id = rg.user_id AND rg.user_id=?");
		List<Claims> claims =new ArrayList<Claims>();
		
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(userQuery);
			pstmt.setInt(1, userID);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				Claims claim = new Claims();      
				 claim.setId(rs.getInt("id"));
				 claim.setProductName(rs.getString("name"));
				 claim.setClaimStatus(rs.getBoolean("claim_status"));
				 claim.setLastModifiedDate(rs.getDate("last_modified_at"));
				 claim.setDescription(rs.getString("description"));
				 claim.setCategory(rs.getString("category"));
				 claims.add(claim);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Claims>(); //Empty Array
		}
		return claims;
	}
	
	public int getClaimCountByRegistration(int regID) {
		int count = 0;
		try{
			String sqlQuery = "SELECT count(registration_id) AS count from claims where registration_id=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, regID);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			} 
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		     return 0;
		}
		return count;
	}

	//DELETE CLAIM TODO
}
