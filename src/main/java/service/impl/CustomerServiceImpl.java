package service.impl;

import dao.CustomerDao;
import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.CustomerService;
import utils.PageBean;

import javax.annotation.Resource;
import java.util.List;

@Service("customerService")
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.NEVER, readOnly = false)
public class CustomerServiceImpl implements CustomerService {
    @Resource(name = "customerDao")
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

    @Override
    public void save(Customer customer) {
        //1维护customer与数据字典对象的关系,由于struts2参数封装,会将参数封装到数据字典的id属性
        //那么我们无需手动维护关系
        //2调用dao保存客户
        cd.savetOrUpdate(customer);

    }

    @Override
    public Customer getById(Long cust_id) {
        return cd.getById(cust_id);
    }

    @Override
    public List<Object[]> getCount(String type) {
        return cd.getTypeCount(type);
    }


    public void setCd(CustomerDao cd) {
        this.cd = cd;
    }
}
