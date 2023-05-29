package Persistence.Hbm;

import Domain.Account;
import Domain.Product;
import Domain.Restaurant;
import Persistence.Generic.RestaurantRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantHBMRepo implements RestaurantRepo {
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
    public Restaurant Save(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant Remove(Long aLong) {
        return null;
    }

    @Override
    public Restaurant Update(Long aLong, Restaurant newer) {
        return null;
    }

    @Override
    public Restaurant FindById(Long restaurantID) {
        initialize();
        Restaurant searched = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                searched = session.createQuery("from Restaurant where id=?", Restaurant.class)
                        .setParameter(0, restaurantID).getSingleResult();
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
    public Iterable<Restaurant> GetAll() {
        initialize();
        List<Restaurant> restaurants = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                restaurants = session.createQuery("from Restaurant",Restaurant.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return restaurants;
    }

    @Override
    public Long Count() {
        return null;
    }
}
