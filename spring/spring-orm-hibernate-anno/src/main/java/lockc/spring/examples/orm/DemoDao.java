package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.Order;

import java.util.List;


public interface DemoDao {

	List<Customer> retrieveCustomers();
	
	Customer retrieveCustomer(long id);
	
	void insertCustomer(Customer customer);

	void insertOrder(Order order);
	
	void close();

}
