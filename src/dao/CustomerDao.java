package dao;

import domain.Customer;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {
    //统计客户数量
    List<Object[]> getTypeCount(String type);
}
