package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;

/**
 * @author Chris
 */
public interface CustomerService {
    void register(Customer customer);
}
