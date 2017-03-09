package lockc.spring.examples.jdbc;

import lockc.spring.examples.jdbc.domain.Customer;

import java.util.List;

public interface DemoDao {

	public List<Customer> retrieveCustomers();
	
	public Customer retrieveCustomer(long id);
	
	public int insertCustomer(Customer customer);

}
