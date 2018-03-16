package dao;

import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {
    public Integer getTotalCount(DetachedCriteria dc) ;

    public List<Customer> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) ;
}
