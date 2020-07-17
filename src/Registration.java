import domain.Customer;
import domain.CustomerDetails;
import domain.Products;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Registration {


    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();
    Customer customer;


    public Registration() {
        signIn();
        purchaseGoods();
    }
    private void signIn() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Input first name");
            String firstName = reader.readLine();

            System.out.println("Input last name");
            String lastName = reader.readLine();

            System.out.println("Input login");
            String login = reader.readLine();

            System.out.println("Input password");
            String password = reader.readLine();

            em.getTransaction().begin();
            customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setLogin(login);
            customerDetails.setPassword(password);
            customer.setCustomerDetails(customerDetails);
            em.getTransaction().commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void purchaseGoods() {
        em.getTransaction().begin();
        customer = em.merge(customer);

        System.out.println("List of goods");
        Query query = em.createQuery("select p.productName from Prices p");
        List<String> list = query.getResultList();
        for (String productName : list) {
            System.out.println(productName);
        }


            while (true) {
                System.out.println("What do you want to buy " +
                        "and how many (specify amount after good)? ");

                String good = new Scanner(System.in).nextLine();
                int amount = new Scanner(System.in).nextInt();


                Query query1 = em.createQuery("select p.price from Prices p where p.productName = :name")
                        .setParameter("name", good);

                List<Long> resultList = query1.getResultList();
                Products products = new Products();
                products.setProductName(good);
                products.setQuantity(amount);
                products.setUnitPrice(resultList.get(0));
                products.getCustomers().add(customer);

                customer.getProducts().add(products);
                System.out.println("Do u want to stop shopping? (yes/no)");
                String choice = new Scanner(System.in).nextLine();
                if(choice.equals("yes")) break;
            }


        em.getTransaction().commit();
    }
}
