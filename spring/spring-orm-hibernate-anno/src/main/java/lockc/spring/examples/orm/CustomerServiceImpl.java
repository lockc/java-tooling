package lockc.spring.examples.orm;

import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Chris
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private DemoDao dao;

    @Transactional
    public void register(Customer customer) {
        dao.insertCustomer(customer);
    }

    @Transactional
    public void placeOrrder(Order order) {
        dao.insertOrder(order);
    }
}
