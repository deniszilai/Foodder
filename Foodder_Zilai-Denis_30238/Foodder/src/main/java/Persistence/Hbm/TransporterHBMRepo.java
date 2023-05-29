package Persistence.Hbm;

import Domain.Account;
import Domain.Restaurant;
import Domain.Transporter;
import Persistence.Generic.TransporterRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class TransporterHBMRepo implements TransporterRepo {
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
    public Transporter Save(Transporter transporter) {
        return null;
    }

    @Override
    public Transporter Remove(Long aLong) {
        return null;
    }

    @Override
    public Transporter Update(Long aLong, Transporter newer) {
        return null;
    }

    @Override
    public Transporter FindById(Long transporterID) {
        initialize();
        Transporter searched = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                searched = session.createQuery("from Transporter where id=?", Transporter.class)
                        .setParameter(0, transporterID).getSingleResult();
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
    public Iterable<Transporter> GetAll() {
        initialize();
        List<Transporter> transporters = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                transporters = session.createQuery("from Transporter",Transporter.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return transporters;
    }


    @Override
    public Long Count() {
        return null;
    }
}
