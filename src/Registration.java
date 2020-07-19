import domain.Customer;
import domain.CustomerDetails;
import domain.Prices;
import domain.Products;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Registration {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();
    Customer customer;


    public Registration() {
        //Main.loadPrices();
        signUp();

    }

    private void signUp() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input first name");
        String firstName = scanner.nextLine();

        System.out.println("Input last name");
        String lastName = scanner.nextLine();

        System.out.println("Input login");
        String login = scanner.nextLine();

        System.out.println("Input password");
        String password = scanner.nextLine();

        em.getTransaction().begin();
        customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setLogin(login);
        customerDetails.setPassword(password);
        customer.setCustomerDetails(customerDetails);
        System.out.println("You signed up successfully ");
        em.getTransaction().commit();

    }
}
