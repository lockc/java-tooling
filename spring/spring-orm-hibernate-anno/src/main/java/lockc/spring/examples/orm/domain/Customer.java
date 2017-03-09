package lockc.spring.examples.orm.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private boolean active;

    /**
     * Uni-directional OneToOne relationship
     */
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pd_id")
	private PersonalDetails personalDetails;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bd_id")
	private BankDetails bankDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Customer() { }
	
	public Customer(String username, boolean active) {
		this.username = username;
		this.active = active;
	}

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
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
