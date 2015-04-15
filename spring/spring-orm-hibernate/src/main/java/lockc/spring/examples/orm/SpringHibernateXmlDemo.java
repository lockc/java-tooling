package lockc.spring.examples.orm;

import java.util.List;

import lockc.spring.examples.demo.domain.Customer;
import lockc.spring.examples.demo.domain.PersonalDetails;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * This example uses Spring's {@link LocalSessionFactoryBean} class which use XML based 
 * configuration and Hibernate mapping files (e.g. Customer.hbm.xml) to wire the domain
 * to Hibernate.
 *  
 * 
 * @author lockc
 *
 */
public class SpringHibernateXmlDemo implements DemoDao {

	private SessionFactory sessionFactory;
	
	public SpringHibernateXmlDemo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Customer> retrieveCustomers() {
		
		List<Customer> customers = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from Customer");
			customers = query.list();
		} finally {
			session.close();
		}
		
		return customers;
	}

	public Customer retrieveCustomer(long id) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (Customer) session.byId(Customer.class).load(id);
		} finally {
			session.close();
		}
	}

	public void insertCustomer(Customer customer) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(customer);
			
			PersonalDetails personalDetails = customer.getPersonalDetails();
			personalDetails.setCustomerId(customer.getCustomerId());
			session.save(personalDetails);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}
	
	public void close() {
		sessionFactory.close();
	}
	
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"orm-hibernate-sqlite-example.xml");
		DemoDao demoDao = (DemoDao) context.getBean("demoDao");
		
		try {
			
			
			PersonalDetails pd = new PersonalDetails();
			pd.setFirstname(RandomStringUtils.randomAlphabetic(10));
			pd.setSurname(RandomStringUtils.randomAlphabetic(10));
			pd.setAddressLine1(RandomStringUtils.randomAlphabetic(15));
			pd.setAddressLine2(RandomStringUtils.randomAlphabetic(15));
			pd.setTown(RandomStringUtils.randomAlphabetic(10));
			pd.setPostcode(RandomStringUtils.randomAlphabetic(7));
			pd.setEmail(RandomStringUtils.randomAlphabetic(30));
			
			Customer newCust = new Customer(RandomStringUtils.randomAlphanumeric(10), true);
			newCust.setPersonalDetails(pd);
			
			
			demoDao.insertCustomer(newCust);
			Customer c = demoDao.retrieveCustomer(20);
			System.out.println(c.toString());
			
			
			List<Customer> customers = demoDao.retrieveCustomers();
			for(Customer customer : customers) {
				System.out.println(customer.toString());
			}
			
			
			
			
		} finally {
			demoDao.close();
			context.destroy();
		}

	}

	

}
