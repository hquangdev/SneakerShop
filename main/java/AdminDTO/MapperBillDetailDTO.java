package AdminDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBillDetailDTO implements RowMapper<BillDetailDTO> {

	@Override
    public BillDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BillDetailDTO billDetailDTO = new BillDetailDTO();
       
        billDetailDTO.setProductName(rs.getString("productName"));
        billDetailDTO.setSize(rs.getInt("selected_size")); 
        billDetailDTO.setPrice(rs.getInt("price"));
        billDetailDTO.setUser(rs.getString("user"));
        billDetailDTO.setPhone(rs.getInt("phone"));
        billDetailDTO.setDisplay_name(rs.getString("display_name"));
        billDetailDTO.setAddress(rs.getString("address"));
        billDetailDTO.setTotal(rs.getDouble("total"));
        billDetailDTO.setQuanty(rs.getInt("quanty"));
        billDetailDTO.setNote(rs.getString("note"));
        billDetailDTO.setStatus(rs.getInt("status"));
        billDetailDTO.setId_bills(rs.getInt("id_bills"));
        
        return billDetailDTO;
    }

}
