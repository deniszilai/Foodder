package Persistence.Hbm;

import Domain.Account;
import Domain.User;
import Persistence.Generic.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHBMRepo implements UserRepo {
    private SessionFactory sessionFactory;

    private void initialize() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Error ocurred " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    private void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Override
    public User Save(User user) {
        return null;
    }

    @Override
    public User Remove(Long aLong) {
        return null;
    }

    @Override
    public User Update(Long aLong, User newer) {
        return null;
    }

    @Override
    public User FindById(Long userID) {
        initialize();
        User searched = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                searched = session.createQuery("from User where id=?", User.class)
                        .setParameter(0, userID).getSingleResult();
                tx.commit();
            } catch (RuntimeException re) {
                if (tx != null)
                    tx.rollback();
                else System.err.println("Error DB Hibernate " + re);
            }
        }
        close();
        return searched;
    }

    @Override
    public Iterable<User> GetAll() {
        initialize();
        List<User> users = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                users = session.createQuery("from User",User.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return users;
    }

    @Override
    public Long Count() {
        return null;
    }

    public UserHBMRepo() {
    }

    @Override
    public User FindByLoginCredentials(String email, String password) {
        initialize();
        User found = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                found = session.createQuery("from User where email=? and password=?", User.class)
                        .setParameter(0, email).setParameter(1, password).getSingleResult();
                tx.commit();
            } catch (RuntimeException re) {
                if (tx != null)
                    tx.rollback();
                else System.err.println("Error hibernate " + re);
            }
        }
        close();
        return found;
    }
}
