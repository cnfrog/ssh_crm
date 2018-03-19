package service;

import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import utils.PageBean;

import java.util.List;

public interface CustomerService {
    PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

    void save(Customer customer);

    /**
     * 根据id获得Customer对象
     */
    Customer getById(Long cust_id);

    //统计分析客户数量
    List<Object[]> getCount(String type);
}
