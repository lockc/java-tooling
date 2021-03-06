package lockc.spring.examples.orm;


import lockc.spring.examples.orm.domain.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *  
 * 
 * @author lockc
 *
 */
public class SpringHibernateAnnotationsDemo {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"orm-hibernate-sqlite-example.xml");

		CustomerService service = context.getBean(CustomerService.class);

        PersonalDetails pd = new PersonalDetails();
        pd.setFirstname(RandomStringUtils.randomAlphabetic(10));
        pd.setSurname(RandomStringUtils.randomAlphabetic(10));
        pd.setAddressLine1(RandomStringUtils.randomAlphabetic(15));
        pd.setAddressLine2(RandomStringUtils.randomAlphabetic(15));
        pd.setTown(RandomStringUtils.randomAlphabetic(10));
        pd.setPostcode(RandomStringUtils.randomAlphabetic(7));
        pd.setEmail(RandomStringUtils.randomAlphabetic(30));

        BankDetails bankDetails = new BankDetails();
        bankDetails.setAccountNumber("12345678");
        bankDetails.setSortCode("12-67-45");

        Customer newCust = new Customer(RandomStringUtils.randomAlphanumeric(10), true);
        newCust.setPersonalDetails(pd);
        newCust.setBankDetails(bankDetails);

        service.register(newCust);



        Order order1 = new Order();
        order1.setDescription("New order - " + RandomStringUtils.randomAlphabetic(5));
        order1.setCustomer(newCust);

        service.placeOrrder(order1);

        Order order2 = new Order();
        order2.setDescription("New order - " + RandomStringUtils.randomAlphabetic(5));
        order2.setCustomer(newCust);

        Product p1 = new Product();
        p1.setName("Product 1");

        order2.getProducts().add(p1);
        service.placeOrrder(order2);
	}
}
