package AdminDTO;

public class BillDetailDTO {
	
	private int id_bills;
	private String productName;
	private int size;
	private double price;
	private String user;
	private int phone;
	private String display_name;
	private String address;
	private double total;
	private int quanty;
	private String note;
	private int status;
	
	public BillDetailDTO() {
		super();
	}

	public int getId_bills() {
		return id_bills;
	}

	public void setId_bills(int id_bills) {
		this.id_bills = id_bills;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BillDetailDTO [id_bills=" + id_bills + ", productName=" + productName + ", size=" + size + ", price="
				+ price + ", user=" + user + ", phone=" + phone + ", display_name=" + display_name + ", address="
				+ address + ", total=" + total + ", quanty=" + quanty + ", note=" + note + "]";
	}

	
	
}
