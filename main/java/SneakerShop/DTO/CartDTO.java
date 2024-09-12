package SneakerShop.DTO;

import AdminDTO.ProductDTO;

public class CartDTO {
	private int quanty;
	private double totalPrice;
	private ProductDTO product;
	private double price;
	private String size;
	

	public CartDTO(int quanty, double totalPrice, ProductDTO product, double price, String size) {
		super();
		this.quanty = quanty;
		this.totalPrice = totalPrice;
		this.product = product;
		this.price = price;
		this.size = size;
	}

	public CartDTO() {
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "CartDTO [quanty=" + quanty + ", totalPrice=" + totalPrice + ", product=" + product + ", price=" + price
				+ ", size=" + size + "]";
	}


	


}
