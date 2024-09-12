package Service.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SneakerShop.Entity.Slides;
import SneakerShop.Entity.Users;
import SneakerShopDAO.UsersDAO;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private UsersDAO usersDAO;

    //Thêm khách hàng
    @Override
    public int AddAcount(Users user) {
        // Không mã hóa mật khẩu
        return usersDAO.AddAcount(user);
    }

    
    
    //Check xem khớp tk và mk ko
    @Override
    public Users CheckAcount(Users user) {
        String pass = user.getPassword();
        user = usersDAO.getUsersByAccount(user);
        if (user != null && pass.equals(user.getPassword())) {
            return user;
        }
        return null;
    }
}
