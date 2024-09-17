package AdminService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import AdminDAO.productDAO;
import AdminDTO.ProductDTO;
import AdminEntity.ProductSize;
import AdminEntity.products;

import java.io.File;

@Service
public class ProductService implements IProductService {
	@Autowired
	private productDAO productDao;

	// Lấy đường dẫn tới thư mục ảnh
	String upImage = ("C:/Users/Admin/eclipse-workspace/SneakerShop/src/main/webapp/assets/user/img-product/");

	// lấy tất cả dữ liệu sản phẩm
	@Override
	public List<ProductDTO> getAllProductDto() {
		return productDao.getAllProducts();
	}

	// Thêm sản phẩm
	public void addProduct(int id_category, String name, int new_product, MultipartFile[] img, int highlight,
			double price, List<ProductSize> productSizeList, int sale, String title, String details)
			throws IOException, SQLException {

		// Xử lý nhiều ảnh
		List<String> imgNames = saveFiles(img);

		// Tạo đối tượng ProductDTO
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId_category(id_category);
		productDTO.setName(name);
		productDTO.setPrice(price);
		productDTO.setSale(sale);
		productDTO.setTitle(title);
		productDTO.setDetails(details);
		productDTO.setHighlight(highlight);
		productDTO.setNew_product(new_product);
		productDTO.setImg(String.join(",", imgNames));

		// Gọi DAO để thêm sản phẩm và lấy id vừa thêm
		int productId = productDao.addProductDto(productDTO); // Giả sử phương thức này trả về id của sản phẩm
		for (ProductSize productSize : productSizeList) {
			productSize.setId_productsize(productId);
			productDao.addProductSize(productSize); // Giả sử phương thức này thêm size và quantity vào bảng
													// `product_sizes`
		}

	}

	// lưu ảnh theo danh sách
	public List<String> saveFiles(MultipartFile[] files) throws IOException {
		List<String> fileNames = new ArrayList<>();
		for (MultipartFile file : files) {
			if (file != null && !file.isEmpty()) {
				String fileName = saveFile(file);
				fileNames.add(fileName);
			}
		}
		return fileNames;
	}

	private String saveFile(MultipartFile file) throws IOException {
		String originalFileName = file.getOriginalFilename();

		String filePath = Paths.get(upImage, originalFileName).toString();

		// Lưu tệp tin vào hệ thống
		Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		// Trả về tên file gốc
		return originalFileName;
	}

	// lấy theo id sản phẩm để xóa
	public products getProductById(long productId) {
		return productDao.getProductById(productId);
	}

	// Phương thức xóa tệp ảnh
	private void deleteImgs(String imgString) {
		String[] imageNames = imgString.split(",");// Tách chuỗi ảnh thành mảng các tên ảnh

		// Duyệt qua từng tên ảnh và xóa tệp
		for (String imageName : imageNames) {
			String trimmedImageName = imageName.trim(); // Loại bỏ khoảng trắng nếu có

			File imageFile = new File(upImage + File.separator + trimmedImageName); // Xác định đường dẫn đầy đủ của ảnh

			// Xóa tệp nếu nó tồn tại
			if (imageFile.exists()) {
				boolean deleted = imageFile.delete();
				if (deleted) {
					System.out.println("Xóa thành công: " + imageFile.getAbsolutePath());
				}
			} else {
				System.out.println("File ko tồn tại " + imageFile.getAbsolutePath());
			}
		}
	}

	@Override
	public int deleteProduct(int id) {
		// Lấy sản phẩm cần xóa
		products deletePr = productDao.getProductById(id);

		if (deletePr != null) {

			String imageStr = deletePr.getImg();// Lấy danh sách các tên ảnh từ sản phẩm
			if (imageStr != null && !imageStr.isEmpty()) {
				String[] imageList = imageStr.split(",");// Chia chuỗi các tên ảnh thành danh sách

				// Xóa tất cả các ảnh liên quan
				for (String imgName : imageList) {
					deleteImgs(imgName);
				}
			}
			return productDao.deleteProduct(id);
		}

		return 0;
	}

//	//phẩn lấy id sản phẩm
	public ProductDTO getProductByIDD(long id_product) {
		// Lấy sản phẩm từ DAO
		ProductDTO product = productDao.getDataProductsid(id_product);
		if (product != null) {
			return product;
		} else {
			System.out.println("Không tìm thấy sản phẩm với ID: " + id_product);
			return null;
		}
	}

	// sửa sản phẩm
	@Override
	public void updateProduct(long id_product, int id_category, String name, int new_product, MultipartFile[] img,
			int highlight, double price, List<ProductSize> productSizeList, int sale, String title, String details)
			throws IOException {

		// Lấy sản phẩm hiện tại từ cơ sở dữ liệu
		ProductDTO productID = getProductByIDD(id_product);

		if (productID != null) {
			// Xóa ảnh cũ nếu có
			deleteImgs(productID.getImg());
			// Xử lý ảnh mới
			if (img != null && img.length > 0) {
				List<String> newImgNames = saveFiles(img); // Lưu ảnh mới và lấy danh sách tên tệp
				productID.setImg(String.join(",", newImgNames)); // Cập nhật danh sách tên ảnh mới cho sản phẩm
			} else {
				// Nếu không có ảnh mới, giữ nguyên ảnh cũ
				productID.setImg(productID.getImg());
			}

			// Cập nhật các thuộc tính khác của sản phẩm
			productID.setName(name);
			productID.setId_category(id_category);
			productID.setNew_product(new_product);
			productID.setPrice(price);
			productID.setSale(sale);
			productID.setTitle(title);
			productID.setHighlight(highlight);
			productID.setDetails(details);
			productID.setProductsize(productSizeList);
			// Cập nhật sản phẩm vào cơ sở dữ liệu
			productDao.updateProduct(productID);

		} else {
			throw new RuntimeException("Product with ID " + id_category + " not found.");
		}

	}

	@Override
	public List<ProductDTO> findByNameContaining(String keyword) {

		return productDao.findByNameContaining(keyword);
	}

	@Override
	public int getTotalShoesInStock() {
		
		return productDao.getTotalShoesInStock();
	}

	@Override
    public int getTotalShoesSold() {
        return productDao.getTotalShoesSold();
    }
}
