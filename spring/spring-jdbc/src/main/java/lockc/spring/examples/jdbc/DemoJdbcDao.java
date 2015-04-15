package lockc.spring.examples.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lockc.spring.examples.demo.domain.Customer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class DemoJdbcDao implements DemoDao {

	/**
	 * This is not required when extending {@link JdbcDaoSupport} but
	 * it is here to demonstrate when you do not because the 
	 * {@link JdbcTemplate} needs to be wired explicitly  
	 */
	private final JdbcTemplate jdbcTemplate;
	
	public DemoJdbcDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * Example using {@link JdbcTemplate#query(String, RowMapper)} to return 
	 * all Customers
	 * 
	 * @return
	 */
	public List<Customer> retrieveCustomers() {
		return jdbcTemplate.query(Sql.SQL_SELECT_ALL_CUSTOMERS, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getLong("ID"), rs.getString("USERNAME"), toBoolean(rs.getString("ACTIVE")));
			}
		});
	}
	
	/**
	 * Example using {@link JdbcTemplate#queryForObject(String, RowMapper, Object...))} method 
	 * to return a single Customer from the database.
	 * 
	 * @param id
	 * @return
	 */
	public Customer retrieveCustomer(long id) {
		return jdbcTemplate.queryForObject(Sql.SQL_SELECT_CUSTOMER_BY_ID, new RowMapper<Customer>(){
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Customer(rs.getLong("ID"), rs.getString("USERNAME"), toBoolean(rs.getString("ACTIVE")));
			}
		}, id);
	}
	
	/**
	 * Example using the {@link JdbcTemplate#update(String, Object...)} method using named 
	 * parameters to insert a new customer
	 * 
	 * @param customer
	 * @return rows affected
	 */
	public int insertCustomer(Customer customer) {
		return jdbcTemplate.update(Sql.SQL_INSERT_CUSTOMER, customer.getUsername(), toYN(customer.isActive()));
	}
	
	
	private boolean toBoolean(String value) {
		if(value == null) return false;
		if(!value.equalsIgnoreCase("y")) return false;
		return true;
	}
	
	private String toYN(boolean value) {
		if(value) return "Y";
		return "N";
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("jdbc-sqlite-example.xml");
		DemoJdbcDao demoDao = (DemoJdbcDao) context.getBean("demoDao");
		
		/* add a new customer */
		demoDao.insertCustomer(new Customer("mbnm", true));
		
		/* list all customers */
		List<Customer> customers = demoDao.retrieveCustomers();
		for(Customer c : customers) {
			System.out.println(c.toString());
		}
		
		context.destroy();
	}
}
