package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String productName;

    private long unitPrice;

    private int quantity;


    @ManyToMany(mappedBy = "products")
    private List<Customer> customers = new ArrayList<>();


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unit_price) {
        this.unitPrice = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
