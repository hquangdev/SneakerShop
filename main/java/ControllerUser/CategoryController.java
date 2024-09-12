package ControllerUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import AdminDTO.ProductDTO;
import Service.user.CategoryServiceImpl;
import Service.user.PaginateServiceImpl;
import SneakerShop.DTO.PaginatesDTO;

@Controller
public class CategoryController extends BaseController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private PaginateServiceImpl paginateService;

    private int totalProductsPage = 9;

    @RequestMapping(value = "/san-pham/{id}")
    public ModelAndView category(@PathVariable String id) {
        _mvShare.setViewName("user/product/category");
        int totalData = categoryService.GetAllProductsByID(Integer.parseInt(id)).size();
        PaginatesDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductsPage, 1);
        _mvShare.addObject("idCategory", id);
        _mvShare.addObject("paginateInfo", paginateInfo);
        _mvShare.addObject("ProductsPaginate", categoryService.GetDataProductsPaginate(Integer.parseInt(id),paginateInfo.getStart(),totalProductsPage));
        return _mvShare;
    }

    @RequestMapping(value = "/san-pham/{id}/{currentPage}")
    public ModelAndView category(@PathVariable String id, @PathVariable String currentPage) {
        _mvShare.setViewName("user/product/category");

        // Lấy tất cả sản phẩm theo ID danh mục
        List<ProductDTO> allProducts = categoryService.GetAllProductsByID(Integer.parseInt(id));
        System.out.println("All Products: " + allProducts);

        int totalData = allProducts.size();

        // Lấy thông tin phân trang
        PaginatesDTO paginateInfo = paginateService.GetInfoPaginates(totalData, totalProductsPage, Integer.parseInt(currentPage));
        System.out.println("Paginate Info: " + paginateInfo);

        // Lấy các sản phẩm trong trang hiện tại
        List<ProductDTO> paginatedProducts = categoryService.GetDataProductsPaginate(Integer.parseInt(id), paginateInfo.getStart(), totalProductsPage);
        System.out.println("Paginated Products: " + paginatedProducts);

        _mvShare.addObject("idCategory", id);
        _mvShare.addObject("paginateInfo", paginateInfo);
        _mvShare.addObject("ProductsPaginate", paginatedProducts);
        
        return _mvShare;
    }


}
