package junit;

import domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibrenateJunit {


    //测试Hibernate
    @Test
    public void  fun1(){
        Configuration cf = new Configuration().configure();
        SessionFactory sf = cf.buildSessionFactory();

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();

        //-----------------------------------------
        User u = new User("jack","杰克","123456");
        //-----------------------------------------
        session.save(u);
        transaction.commit();
        session.close();










    }


}
