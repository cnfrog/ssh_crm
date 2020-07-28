package dao.impl;

import dao.UserDao;
import domain.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Resource(name = "sessionFactory")
    public void setSf(SessionFactory sf) {
        super.setSessionFactory(sf);
    }

    @Override
    public User findUserByUserCode(String user_code) {
        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
        dc.add(Restrictions.eq("user_code", user_code));

        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);

        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }


    }

}
