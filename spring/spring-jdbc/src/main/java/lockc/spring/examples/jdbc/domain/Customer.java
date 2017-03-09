package lockc.spring.examples.jdbc.domain;

public class Customer {

	private long customerId;
	private String username;
	private boolean active;
	private PersonalDetails personalDetails;
	private BankDetails bankDetails;
	
	public Customer() { }
	
	public Customer(long customerId, String username, boolean active) {
		this.customerId = customerId;
		this.username = username;
		this.active = active;
	}
	
	public Customer(String username, boolean active) {
		this.username = username;
		this.active = active;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	@Override
	public String toString() {
		return String.format("Customer ID: %d Username: %s", customerId, username);
	}
}
