package Service.user;

import org.springframework.stereotype.Service;
import SneakerShop.DTO.PaginatesDTO;

@Service
public class PaginateServiceImpl {

    public PaginatesDTO GetInfoPaginates(int totalData, int limit, int currentPage) {
        PaginatesDTO paginate = new PaginatesDTO();
        paginate.setLimit(limit);
        paginate.setTotalPage(SetInfoTotalPage(totalData, limit));
        paginate.setCurrentPage(CheckCurrentPage(currentPage, paginate.getTotalPage()));
        int start = FindStart(paginate.getCurrentPage(), limit);
        paginate.setStart(start);
        paginate.setEnd(FindEnd(paginate.getStart(), limit, totalData));
        return paginate;
    }

    private int FindEnd(int start, int limit, int totalData) {
        return start + limit > totalData ? totalData : (start + limit) -1;
    }

    private int FindStart(int currentPage, int limit) {
        return ((currentPage - 1) * limit) + 1 ;
    }

    private int SetInfoTotalPage(int totalData, int limit) {
        int totalPage = 0;
        totalPage = totalData / limit;
        totalPage = totalPage * limit < totalData ? totalPage + 1 : totalPage;
        return totalPage;
    }

    public int CheckCurrentPage(int currentPage, int totalPage) {
        if (currentPage < 1) {
            return 1;
        }
        if (currentPage > totalPage) {
            return totalPage;
        }
        return currentPage;
    }
}
