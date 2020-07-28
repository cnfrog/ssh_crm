package dao.impl;

import dao.CustomerDao;
import domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    @Resource(name = "sessionFactory")
    public void setSf(SessionFactory sf) {
        super.setSessionFactory(sf);
    }

    @Override
    public List<Object[]> getTypeCount(final String type) {

        List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
            String sql = "SELECT\n" +
                    "\tbd.dict_item_name,\n" +
                    "\tcount(*)\n" +
                    "FROM\n" +
                    "\tcst_customer AS c\n" +
                    "INNER JOIN base_dict AS bd\n" +
                    "WHERE\n" +
                    "\tbd.dict_id = c.cust_" + type + "\n" +
                    "GROUP BY\n" +
                    "\tc.cust_" + type;

            @Override
            public List doInHibernate(Session session) throws HibernateException {
                SQLQuery query = session.createSQLQuery(sql);
                return query.list();
            }
        });
        return list;
    }


}
