package lockc.spring.examples.orm.domain;

import javax.persistence.*;

@Entity
@Table(name = "bank_details")
public class BankDetails {
	@Id
    @GeneratedValue
	private long id;
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "sort_code")
	private String sortCode;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getSortCode() {
		return sortCode;
	}
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}
}
