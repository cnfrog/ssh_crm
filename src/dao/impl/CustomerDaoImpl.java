package dao.impl;

import dao.CustomerDao;
import domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    @Override
    public Integer getTotalCount(DetachedCriteria dc) {
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(dc);
        // 清空之前设置的聚合函数
        dc.setProjection(null);
        if (list != null && list.size() > 0) {
            Long count = list.get(0);
            return count.intValue();
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
        System.out.println(start+" PageSize:"+pageSize);
        List<Customer> list = (List<Customer>) getHibernateTemplate().findByCriteria(dc, start, pageSize);
        System.out.println("list"+list);
        return list;
    }
}
