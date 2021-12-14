package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import database.Database;
import registration.model.Registrations;

public class RegistrationDao {
	private static RegistrationDao instance;

	private Connection connection;

	public RegistrationDao() {
		connection = Database.getConnection();
	}

	// SingleTon
	public static RegistrationDao getRegistrationDaoInstance() {
		if (instance == null) {
			return new RegistrationDao();
		}
		return instance;
	}
	
	public int createProductRegistration(Registrations registrations) {
		String sqlQuery = "INSERT INTO registrations(user_id, product_id,name, purchase_date) VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, registrations.getUserId());
			pstmt.setInt(2, registrations.getProductID());
			pstmt.setString(3, registrations.getName());
			pstmt.setDate(4, new java.sql.Date(registrations.getPurchaseDate().getTime()));
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public List<Registrations> getUserRegistrations(int userID) {
		String sqlQuery = "SELECT r.*,p.type FROM registrations r JOIN products p \r\n"
				+ "ON p.id = r.product_id\r\n"
				+ "WHERE user_id=?";
		List<Registrations> registrations =new ArrayList<Registrations>();
		
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, userID);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				 Registrations reg = new Registrations();      
				 reg.setId(rs.getInt("id"));
				 reg.setUserId(rs.getInt("user_id"));
				 reg.setProductID(rs.getInt("product_id"));
				 reg.setName(rs.getString("name"));
				 reg.setRegistrationDate(rs.getDate("registration_date"));
				 reg.setPurchaseDate(rs.getDate("purchase_date"));
				 reg.setIsCreatedAt(rs.getDate("is_created_at"));
				 reg.setProductType(rs.getString("type"));
				 registrations.add(reg);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Registrations>(); //Empty Array
		}
		return registrations;
	}
	
	public Registrations validateRegistrationDetails(int userID, int regID, int productID) {
		Registrations registration = null;
		try{
			String sqlQuery = "SELECT id, registration_date FROM registrations WHERE user_id=? AND id=? OR product_id=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, regID);
			pstmt.setInt(3, productID);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				registration = new Registrations();
				registration.setId(rs.getInt("id"));
				registration.setRegistrationDate(rs.getDate("registration_date"));
			}
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		     return null;
		}
		return registration;
	}

}
