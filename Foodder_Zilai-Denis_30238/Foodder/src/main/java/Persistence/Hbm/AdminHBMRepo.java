package Persistence.Hbm;

import Domain.Admin;
import Domain.User;
import Persistence.Generic.AdminRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class AdminHBMRepo implements AdminRepo {
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
    public Admin Save(Admin admin) {
        return null;
    }

    @Override
    public Admin Remove(Long aLong) {
        return null;
    }

    @Override
    public Admin Update(Long aLong, Admin newer) {
        return null;
    }

    @Override
    public Admin FindById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Admin> GetAll() {
        initialize();
        List<Admin> admins = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                admins = session.createQuery("from Admin",Admin.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return admins;
    }

    @Override
    public Long Count() {
        return null;
    }
}
