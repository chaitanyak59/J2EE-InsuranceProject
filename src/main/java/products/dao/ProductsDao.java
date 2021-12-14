package products.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import database.Database;
import helpers.app.AppHelpers;
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
	
	public List<Products> getAllProducts(String searchParam) {
		List<Products> products =new ArrayList<Products>();
		String searchQuery;
    	PreparedStatement pstmt;
		try {
			if(searchParam == null || searchParam.length() == 0) {
				searchQuery = "SELECT * FROM products;";
				pstmt = (PreparedStatement) connection.prepareStatement(searchQuery);
			} else {
				searchQuery = "SELECT * FROM products where name=? OR type=? OR serial_no=?";
				pstmt = (PreparedStatement) connection.prepareStatement(searchQuery);
				pstmt.setString(1, searchParam);
				pstmt.setString(2, searchParam);
				pstmt.setString(3, searchParam);
			}
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
	
	public int getProductBySerial(String serial_no) {
		int productID = AppHelpers.INVALID_PRODUCT;
		try{
			String sqlQuery = "SELECT id FROM products WHERE serial_no=?";
			PreparedStatement pstmt = (PreparedStatement) connection.prepareStatement(sqlQuery);
			pstmt.setString(1, serial_no);
			ResultSet rs= pstmt.executeQuery();
			while(rs.next()) {
				productID = rs.getInt(1);
			}
			pstmt.close();
		} catch(Exception e){
		     e.printStackTrace();
		     return productID;
		}
		return productID;
	}
}
