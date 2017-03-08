package lockc.spring.examples.orm.domain;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String username;

	@Column
	private boolean active;

    /**
     * Uni-directional OneToOne relationship
     */
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PD_ID", unique=true)
	private PersonalDetails personalDetails;


//	@OneToOne
//	private BankDetails bankDetails;
	
	public Customer() { }
	
	public Customer(String username, boolean active) {
		this.username = username;
		this.active = active;
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//	public BankDetails getBankDetails() {
//		return bankDetails;
//	}
//
//	public void setBankDetails(BankDetails bankDetails) {
//		this.bankDetails = bankDetails;
//	}

	@Override
	public String toString() {
		return String.format("Customer ID: %d Username: %s", id, username);
	}
}
