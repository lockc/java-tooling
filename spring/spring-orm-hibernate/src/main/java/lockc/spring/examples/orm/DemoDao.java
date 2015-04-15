package lockc.spring.examples.orm;

import java.util.List;

import lockc.spring.examples.demo.domain.Customer;

public interface DemoDao {

	public List<Customer> retrieveCustomers();
	
	public Customer retrieveCustomer(long id);
	
	public void insertCustomer(Customer customer);
	
	public void close();

}
