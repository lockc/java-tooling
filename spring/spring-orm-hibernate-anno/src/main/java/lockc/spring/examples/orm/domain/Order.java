package lockc.spring.examples.orm.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Chris
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        if(Objects.isNull(products)){
            products = new ArrayList<Product>();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
