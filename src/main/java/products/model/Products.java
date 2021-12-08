package products.model;

import java.util.Date;

public class Products {
	private int id;
	private String name;
	private String type;
	private String serial_no;
	private String price;
	private Date isCreatedAt;
	
	public Products() {
		super();
	}
	public Products(int id, String name, String type, String serial_no, String price, Date isCreatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.serial_no = serial_no;
		this.price = price;
		this.isCreatedAt = isCreatedAt;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getIsCreatedAt() {
		return isCreatedAt;
	}
	public void setIsCreatedAt(Date isCreatedAt) {
		this.isCreatedAt = isCreatedAt;
	}
}
