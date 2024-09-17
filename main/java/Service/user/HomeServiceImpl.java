package Service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AdminDTO.ProductDTO;
import AdminService.ProductService;
import SneakerShop.Entity.Categorys;
import SneakerShop.Entity.Contact;
import SneakerShop.Entity.Menus;
import SneakerShop.Entity.Slides;
import SneakerShopDAO.CategorysDAO;
import SneakerShopDAO.ContactDAO;
import SneakerShopDAO.MenusDAO;
import SneakerShopDAO.ProductsDAO;
import SneakerShopDAO.SlidesDAO;

@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private SlidesDAO slidesDAO;

    @Autowired
    private CategorysDAO categorysDAO;
    
    @Autowired
    private MenusDAO menusDAO;
    
    @Autowired
    private ProductsDAO productsDAO;
    
    @Autowired
    private ContactDAO contactDao;
    
    @Override
    public List<Slides> GetDataSlide() {
        return slidesDAO.GetDataSlide();
    }

    public List<Categorys> GetDataCategorys() {
        return categorysDAO.GetDataCategorys();
    }

	public List<Menus> GetDataMenus() {
		return menusDAO.GetDataMenus();
	}
	
	//Lấy dữ liệu theo sản phẩm mới
	public List<ProductDTO> GetNewProducts() {
		List<ProductDTO> listProducts = productsDAO.GetDataNewProduct();
		return listProducts;
	}
	
	//Lấy theo sản phẩm nổi bật
	public List<ProductDTO> GetHighlightProduct(){
		List<ProductDTO> listProduct = productsDAO.GetDataHighlightProduct();
		return listProduct;	
	}
	
	//lấy theo sản phẩm thường
	public List<ProductDTO> GetProduct(){
		List<ProductDTO> listProduct = productsDAO.GetDataProduct();
		return listProduct;	
	}
	
	//lấy dữ liệu contact
	public List<Contact> GetContact(){
		List<Contact> listContact = contactDao.getAllContact();
		return listContact;
	}
	
	

}
