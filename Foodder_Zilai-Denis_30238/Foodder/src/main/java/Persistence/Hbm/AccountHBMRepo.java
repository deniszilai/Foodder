package Persistence.Hbm;

import Domain.Account;
import Domain.User;
import Persistence.Generic.AccountRepo;
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
public class AccountHBMRepo implements AccountRepo {
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
    public Account Save(Account account) {
        return null;
    }

    @Override
    public Account Remove(Long accountID) {
        Account found = FindById(accountID);
        if(found == null)
            return null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                session.delete(found);
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return found;
    }

    @Override
    public Account Update(Long aLong, Account newer) {
        return null;
    }

    @Override
    public Account FindById(Long accountID) {
        initialize();
        Account searched = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                searched = session.createQuery("from Account where id=?", Account.class)
                        .setParameter(0, accountID).getSingleResult();
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
    public Iterable<Account> GetAll() {
        initialize();
        List<Account> accounts = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                accounts = session.createQuery("from Account",Account.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return accounts;
    }

    @Override
    public Long Count() {
        return null;
    }
}
