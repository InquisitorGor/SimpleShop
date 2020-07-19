package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@NamedNativeQueries({
        @NamedNativeQuery(name = Customer.GET_LIST_OF_GOODS,
                query = "select p.product_name, p.quantity, p.quantity * p.unitprice AS FinalSum " +
                        "from product AS p " +
                        "join customer_products AS cP using(product_id) " +
                        "join customer c2 on cP.customer_id = c2.id " +
                        "join customer_details cD on c2.id = cD.customer_details_id " +
                        "where customer_details.login = :login and customer_details.password"
        )
})
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
