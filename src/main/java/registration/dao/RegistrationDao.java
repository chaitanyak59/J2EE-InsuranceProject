package registration.dao;

import java.sql.Connection;
import java.sql.Date;
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
	
	public List<Registrations> getAllRegistrations(int userID) {
		String sqlQuery = "SELECT * FROM registrations WHER user_id=?;";
		List<Registrations> registrations =new ArrayList<Registrations>();
		
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, userID);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				 Registrations reg = new Registrations();      
				 reg.setId(rs.getInt(1));
				 reg.setUserId(rs.getInt(2));
				 reg.setProductID(rs.getInt(3));
				 reg.setRegistrationDate(rs.getDate(4));
				 reg.setPurchaseDate(rs.getDate(5));
				 reg.setIsCreatedAt(rs.getDate(6));
				 registrations.add(reg);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Registrations>(); //Empty Array
		}
		return registrations;
	}

}
