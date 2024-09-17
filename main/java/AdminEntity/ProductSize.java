package AdminEntity;

public class ProductSize {
	private int id;
	private long id_productsize;
	private int sizes;
	private int quantity;

	public ProductSize() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getId_productsize() {
		return id_productsize;
	}

	public void setId_productsize(long id_productsize) {
		this.id_productsize = id_productsize;
	}

	public int getSizes() {
		return sizes;
	}

	public void setSizes(int sizes) {
		this.sizes = sizes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductSize [id=" + id + ", id_productsize=" + id_productsize + ", sizes=" + sizes + ", quantity="
				+ quantity + "]";
	}

}
