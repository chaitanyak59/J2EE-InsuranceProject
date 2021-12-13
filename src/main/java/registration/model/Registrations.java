package registration.model;

import java.util.Date;

public class Registrations {
	private int id;
	private int userId;
	private int productID;
	private Date registrationDate;
	private Date purchaseDate;
	private Date isCreatedAt;
	private String name;
	private String productType;
	
	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Registrations() {}
	
	
	public Registrations(int id, int userId, int productID, String name, Date purchaseDate, Date registrationDate, Date isCreatedAt, String productType) {
		super();
		this.id = id;
		this.userId = userId;
		this.productID = productID;
		this.registrationDate = registrationDate;
		this.purchaseDate = purchaseDate;
		this.isCreatedAt = isCreatedAt;
		this.name = name;
		this.productType = productType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getIsCreatedAt() {
		return isCreatedAt;
	}
	public void setIsCreatedAt(Date isCreatedAt) {
		this.isCreatedAt = isCreatedAt;
	}
	
	

}
