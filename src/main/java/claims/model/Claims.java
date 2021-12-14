package claims.model;

import java.util.Date;

public class Claims {
	
	private int id;
	private int registrationID;
	private boolean claimStatus;
	private Date lastModifiedDate;
	private String description;
	private String category;
	
	/*Below used while retrieving only*/
	private String userEmail;
	private String productName;
	
	public Claims(int id, int registrationID, boolean claimStatus, Date lastModifiedDate, String description,
			String category) {
		super();
		this.id = id;
		this.registrationID = registrationID;
		this.claimStatus = claimStatus;
		this.lastModifiedDate = lastModifiedDate;
		this.description = description;
		this.category = category;
	}
	
	public Claims() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRegistrationID() {
		return registrationID;
	}
	public void setRegistrationID(int registrationID) {
		this.registrationID = registrationID;
	}
	public boolean isClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(boolean claimStatus) {
		this.claimStatus = claimStatus;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
