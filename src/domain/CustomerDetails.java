package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_details")
@NamedQueries({
        @NamedQuery(name = CustomerDetails.GET_CUSTOMER_BY_LOGIN_AND_PASSWORD,
                query = "select c " +
                        "from CustomerDetails cD " +
                        "join Customer c on c.customerDetails = cD.customer.customerDetails "  +
                        "where  cD.login = :login and cD.password = :password")
})
public class CustomerDetails {
    @Id
    @GeneratedValue
    @Column(name = "customer_details_id")
    private int id;

    public static final String GET_CUSTOMER_BY_LOGIN_AND_PASSWORD = "Prices.getCustomerByLoginAndPassword";

    private String login;

    private String password;

    @OneToOne(mappedBy = "customerDetails")
    private Customer customer;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "registration_day")
    private Date date = new Date();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
