package SneakerShopDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import SneakerShop.Entity.MaperMenus;
import SneakerShop.Entity.Menus;

@Repository
public class MenusDAO extends BaseDAO  {

    public List<Menus> GetDataMenus() {
    	List<Menus> list = new ArrayList<Menus>();
        String sql = "SELECT * FROM menus";
        list = _jdbcTemplate.query(sql, new MaperMenus());
        return list;
    }
}
