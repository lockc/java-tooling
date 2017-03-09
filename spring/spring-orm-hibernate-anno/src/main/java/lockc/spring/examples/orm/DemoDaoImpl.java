package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chris
 */
@Repository
public class DemoDaoImpl implements DemoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> retrieveCustomers() {
        Session session = sessionFactory.getCurrentSession();
        List<Customer> customers = session.createCriteria(Customer.class).list();
        return customers;
    }

    public Customer retrieveCustomer(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Customer) session.byId(Customer.class).load(id);
    }

    public void insertCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    public void insertOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    public void close() {
        sessionFactory.close();
    }
}
