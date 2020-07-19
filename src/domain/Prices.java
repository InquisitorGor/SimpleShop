package domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Prices.GET_PRODUCT_NAME_AND_PRICE,
                query = "select p.productName, p.price from Prices p"),
        @NamedQuery(name = Prices.FIND_PRICE_BY_NAME,
                query = "select p.price from Prices p where p.productName = :name")
})
public class Prices {
    @Id
    @GeneratedValue
    private int id;

    public static final String GET_PRODUCT_NAME_AND_PRICE = "Prices.getProductNameAndPrice";
    public static final String FIND_PRICE_BY_NAME = "Prices.findPriceByName";

    @Column(name = "product_name")
    private String productName;

    private long price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
