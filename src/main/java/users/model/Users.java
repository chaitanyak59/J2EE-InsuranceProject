package users.model;

import java.util.Date;

public class Users {
	private int id;
	private String name;
	private String email;
	private String hash;
	private byte[] salt;
	private String address;
	private int role_id;
	private Date isCreatedAt;
	private String mobileNo;
	public Users() {
		
	}
	public Users(int id, String name, String email, String hash, byte[] salt, String address,String mobileNo, int role_id,
			Date isCreatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.hash = hash;
		this.salt = salt;
		this.address = address;
		this.role_id = role_id;
		this.isCreatedAt = isCreatedAt;
		this.mobileNo = mobileNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public byte[] getSalt() {
		return salt;
	}
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getIsCreatedAt() {
		return isCreatedAt;
	}
	public void setIsCreatedAt(Date isCreatedAt) {
		this.isCreatedAt = isCreatedAt;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

}
