package dao.impl;

import dao.LinkManDao;
import domain.LinkMan;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao{

    @Resource(name = "sessionFactory")
    public void setSf(SessionFactory sf) {
        super.setSessionFactory(sf);
    }

}
