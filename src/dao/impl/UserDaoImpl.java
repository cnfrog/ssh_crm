package dao.impl;

import dao.UserDao;
import domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

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


        //Hql
//        return getHibernateTemplate().execute(new HibernateCallback<User>() {
//            @Override
//            public User doInHibernate(Session session) throws HibernateException {
//                String hql="from User where user_code=?";
//                Query query = session.createQuery(hql);
//                User user = (User) query.uniqueResult();
//                return user;
//            }
//        });


    }
}
