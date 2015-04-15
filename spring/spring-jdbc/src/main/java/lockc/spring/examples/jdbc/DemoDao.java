package lockc.spring.examples.jdbc;

import java.util.List;

import lockc.spring.examples.demo.domain.Customer;

public interface DemoDao {

	public List<Customer> retrieveCustomers();
	
	public Customer retrieveCustomer(long id);
	
	public int insertCustomer(Customer customer);

}
