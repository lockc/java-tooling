package lockc.spring.examples.orm;


import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.PersonalDetails;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 *
 *  
 * 
 * @author lockc
 *
 */
public class SpringHibernateAnnotationsDemo implements DemoDao {

	private SessionFactory sessionFactory;

	public SpringHibernateAnnotationsDemo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Customer> retrieveCustomers() {
        Session session = null;
		try {
			session = sessionFactory.openSession();
            List<Customer> customers = session.createCriteria(Customer.class).list();
            return customers;
		} finally {
			session.close();
		}
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
		DemoDao demoDao = (DemoDao) context.getBean("demoDaoAnnotations");

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
			
			
			List<Customer> customers = demoDao.retrieveCustomers();
			for(Customer customer : customers) {
				System.out.println(customer);
			}
			
			
			
			
		} finally {
			demoDao.close();
			context.destroy();
		}

	}

	

}
