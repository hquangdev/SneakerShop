package AdminDTO;

import java.sql.Date;
import java.util.List;


import AdminEntity.ProductSize;

public class ProductDTO {
	
	private long id_product;
	private int id_category;
	private String name;
	private double price;
	private int sale;
	private String title;
	private int highlight;
	private int new_product;
	private String details;
	private String img;
	private Date created_at;
	private Date updated_at;
    private List<ProductSize> productsize;
   
   	public ProductDTO() {
   		super();
   		}

	public long getId_product() {
		return id_product;
	}

	public void setId_product(long id_product) {
		this.id_product = id_product;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getHighlight() {
		return highlight;
	}

	public void setHighlight(int highlight) {
		this.highlight = highlight;
	}

	public int getNew_product() {
		return new_product;
	}

	public void setNew_product(int new_product) {
		this.new_product = new_product;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<ProductSize> getProductsize() {
		return productsize;
	}

	public void setProductsize(List<ProductSize> productsize) {
		this.productsize = productsize;
	}

	@Override
	public String toString() {
		return "ProductDTO [id_product=" + id_product + ", id_category=" + id_category + ", name=" + name + ", price="
				+ price + ", sale=" + sale + ", title=" + title + ", highlight=" + highlight + ", new_product="
				+ new_product + ", details=" + details + ", img=" + img + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", productsize=" + productsize + "]";
	}

	
}
