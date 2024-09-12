package AdminDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class BaseAdmin {
	@Autowired
    public JdbcTemplate _jdbcTemplate;

}
