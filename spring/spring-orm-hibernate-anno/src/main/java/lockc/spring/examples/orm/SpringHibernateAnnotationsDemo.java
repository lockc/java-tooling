package lockc.spring.examples.orm;


import lockc.spring.examples.orm.domain.BankDetails;
import lockc.spring.examples.orm.domain.Customer;
import lockc.spring.examples.orm.domain.PersonalDetails;
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
	}
}
