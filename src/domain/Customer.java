package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private int id;

    public static final String GET_LIST_OF_GOODS = "Prices.getListOfGoods";

    private String firstName;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_details")
    private CustomerDetails customerDetails;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_products",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products = new ArrayList<>();


    @PostPersist
    private void info() {
        System.out.println("Customer was added successfully");
    }

    public double getTotalSum(){
        double sum = 0.0;
        for (Products product:products) {
            sum += product.getQuantity() * product.getUnitPrice();
        }
        return sum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
        customerDetails.setCustomer(this);
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}
