package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.Order;

/**
 * @author Chris
 */
public interface CustomerService {
    void register(Customer customer);

    void placeOrrder(Order order);
}
