package lockc.spring.examples.orm;

import java.util.List;

import lockc.spring.examples.demo.domain.Customer;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * This example does not use Spring at all, it intialises and configures 
 * Hibernate itself using a Hibernate configuration file (hibernate.cfg.xml) 
 * 
 * @author lockc
 *
 */
public class NoSpringHibernateDemo implements DemoDao {

	private SessionFactory sessionFactory;
	
	public NoSpringHibernateDemo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public List<Customer> retrieveCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer retrieveCustomer(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertCustomer(Customer customer) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(customer);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	public void close() {
		sessionFactory.close();
	}
	
	
	public static void main(String[] args) {
		
		DemoDao demoDao = new NoSpringHibernateDemo(
				new Configuration().configure().buildSessionFactory());
		
		try {
			demoDao.insertCustomer(new Customer("userHib5", true ));
		} finally {
			demoDao.close();
		}
	}



}
