package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;

import java.util.List;


public interface DemoDao {

	public List<Customer> retrieveCustomers();
	
	public Customer retrieveCustomer(long id);
	
	public void insertCustomer(Customer customer);
	
	public void close();

}
