package Service.user;

import org.springframework.stereotype.Service;

import SneakerShop.Entity.Users;
@Service
public interface IAccountService {
    public int AddAcount(Users user);
	public Users CheckAcount(Users user);
}
