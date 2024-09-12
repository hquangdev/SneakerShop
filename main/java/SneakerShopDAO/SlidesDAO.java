package SneakerShopDAO;

import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import SneakerShop.Entity.MaperSlides;
import SneakerShop.Entity.Slides;

@Service
public class SlidesDAO extends BaseDAO {

	public List<Slides> GetDataSlide() {
		String sql = "SELECT * FROM slides";
		return _jdbcTemplate.query(sql, new MaperSlides());
	}

	public List<Slides> getAllSlides() {
		String sql = "SELECT * FROM slides";
		return _jdbcTemplate.query(sql, new MaperSlides());
	}

	// lấy theo id
	@SuppressWarnings("deprecation")
	public Slides getSlideById(int id) {
		String sql = "SELECT * FROM slides WHERE id = ?";
		return _jdbcTemplate.queryForObject(sql, new Object[] { id }, new MaperSlides());
	}

	// Xóa slide
	public int deleteSlide(int id) {
		String sql = "DELETE FROM slides WHERE id = ?";
		return _jdbcTemplate.update(sql, id);
	}

	// Thêm slide
	public void saveSlide(Slides slide) {
		if (slide.getId() == 0) {
			String sql = "INSERT INTO slides (img, caption, content) VALUES (?, ?, ?)";
			_jdbcTemplate.update(sql, slide.getImg(), slide.getCaption(), slide.getContent());
		} else {
			String sql = "UPDATE slides SET img = ?, caption = ?, content = ? WHERE id = ?";
			_jdbcTemplate.update(sql, slide.getImg(), slide.getCaption(), slide.getContent(), slide.getId());
		}
	}

	// chức năng sửa slide
	public void editSlide(Slides slide) {
		String sql ="UPDATE slides SET img = ?, caption = ?, content = ? WHERE id = ?";
		_jdbcTemplate.update(sql, slide.getImg(), slide.getCaption(), slide.getContent(), slide.getId());
	}
		
	 // Phân trang slide
	@SuppressWarnings("deprecation")
	public List<Slides> getSlideWithPagination(int pageNumber, int pageSize) {
	    int offset = (pageNumber - 1) * pageSize;
	    String sql = "SELECT * FROM slides LIMIT ? OFFSET ?";
	    return _jdbcTemplate.query(sql, new Object[]{pageSize, offset}, new MaperSlides());
	}

	public int getTotalSlideCount() {
	    String sql = "SELECT COUNT(*) FROM slides";
	    return _jdbcTemplate.queryForObject(sql, Integer.class);
	}

}