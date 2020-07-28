package dao.impl;

import dao.BaseDao;
import dao.SaleVisitDao;
import domain.SaleVisit;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {

    @Resource(name = "sessionFactory")
    public void setSf(SessionFactory sf) {
        super.setSessionFactory(sf);
    }

}
