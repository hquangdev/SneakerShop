package Service.user;

import org.springframework.stereotype.Service;

import SneakerShop.DTO.PaginatesDTO;

@Service
public interface IPaginateService {
	public PaginatesDTO GetInfoPaginates(int totalPage, int limit, int currentPage);
}
