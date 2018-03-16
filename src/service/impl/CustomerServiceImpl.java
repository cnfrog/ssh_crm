package service.impl;

import dao.CustomerDao;
import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import service.CustomerService;
import utils.PageBean;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao cd;

    @Override
    public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
        // 调用dao查询总记录数
        Integer totalCount = cd.getTotalCount(dc);

        // 创建PageBean对象
        PageBean pb = new PageBean(currentPage, totalCount, pageSize);

        // 调用dao查询分页列表数据
        List<Customer> list = cd.getPageList(dc, pb.getStart(), pb.getPageSize());
        // 列表数据放入pageBean中,并返回
        pb.setList(list);

        return pb;
    }

    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
