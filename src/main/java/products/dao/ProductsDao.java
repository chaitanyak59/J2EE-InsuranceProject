package products.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import database.Database;
import products.model.Products;

public class ProductsDao {
	private static ProductsDao instance;

	private Connection connection;

	public ProductsDao() {
		connection = Database.getConnection();
	}

	// SingleTon
	public static ProductsDao getProductsDaoInstance() {
		if (instance == null) {
			return new ProductsDao();
		}
		return instance;
	}
	
	public int createProduct(Products product) {
		String sqlQuery = "INSERT INTO products(name,type,serial_no,price) VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getType());
			pstmt.setString(3, product.getSerial_no());
			pstmt.setString(4, product.getPrice());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int deleteProduct(int productID) {
		String sqlQuery = "DELETE FROM products WHERE id = ?";
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, productID);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public List<Products> getAllProducts() {
		String sqlQuery = "SELECT * FROM products;";
		List<Products> products =new ArrayList<Products>();
		
		try {
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				 Products product = new Products();      
				 product.setId(rs.getInt(1));
				 product.setName(rs.getString(2));
				 product.setType(rs.getString(3));
				 product.setSerial_no(rs.getString(4));
				 product.setPrice(rs.getString(5));
				 product.setIsCreatedAt(rs.getDate(6));
				 products.add(product);
				} 
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Products>(); //Empty Array
		}
		return products;
	}
	
	public boolean isValidProductBySerial(String serial_no) {
		boolean isValid = false;
		try{
			String sqlQuery = "SELECT id,name FROM products WHERE serial_no=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, serial_no);
			ResultSet rs= pstmt.executeQuery();
			if(rs.isBeforeFirst()) {
				isValid = true;
			} 
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		     return false;
		}
		return isValid;
	}
}
