package lockc.spring.examples.jdbc;

public interface Sql {

	public static final String SQL_SELECT_ALL_CUSTOMERS = "select ID, USERNAME, ACTIVE from customers";
	public static final String SQL_SELECT_CUSTOMER_BY_ID = "select ID, USERNAME, ACTIVE from customers where ID = :id";
	public static final String SQL_INSERT_CUSTOMER = "insert into customers (USERNAME, ACTIVE) values (:username, :active)";
}
