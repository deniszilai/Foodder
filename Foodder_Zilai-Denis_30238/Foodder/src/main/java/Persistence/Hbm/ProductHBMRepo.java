package Persistence.Hbm;

import Domain.Account;
import Domain.Order;
import Domain.Product;
import Persistence.Generic.ProductRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProductHBMRepo implements ProductRepo {
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
    public Product Save(Product product) {
        return null;
    }

    @Override
    public Product Remove(Long aLong) {
        return null;
    }

    @Override
    public Product Update(Long aLong, Product newer) {
        return null;
    }

    @Override
    public Product FindById(Long productID) {
        initialize();
        Product searched = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                searched = session.createQuery("from Product where id=?", Product.class)
                        .setParameter(0, productID).getSingleResult();
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
    public Iterable<Product> GetAll() {
        initialize();
        List<Product> products = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                products = session.createQuery("from Product",Product.class).getResultList();
                tx.commit();
            }catch (RuntimeException re){
                if(tx!=null)
                    tx.rollback();
                else System.out.println("Error DB Hibernate " + re);
            }
        }
        close();
        return products;
    }

    @Override
    public Long Count() {
        return null;
    }
}
