import domain.Customer;
import domain.CustomerDetails;
import domain.Prices;
import domain.Products;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Authorization {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();
    Customer customer;

    public Authorization() {
        signIn();
    }


    private void purchaseGoods() {
        em.getTransaction().begin();

        System.out.println("List of goods");
        Query query = em.createNamedQuery(Prices.GET_PRODUCT_NAME_AND_PRICE);
        List<Object[]> list = query.getResultList();
        for (Object[] p : list) {
            System.out.print(p[0] + " ");
            System.out.print(p[1] + "\n");
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do u want to stop shopping? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equals("yes")) break;

            System.out.println("What do you want to buy " +
                    "and how many (specify amount after good)? ");
            String good = scanner.nextLine();
            int amount = Integer.parseInt(scanner.nextLine());

            Query query1 = em.createNamedQuery(Prices.FIND_PRICE_BY_NAME).setParameter("name", good);
            Long price = (Long) query1.getSingleResult();

            Products products = new Products();
            products.setProductName(good);
            products.setQuantity(amount);
            products.setUnitPrice(price);

            products.getCustomers().add(customer);
            customer.getProducts().add(products);


        }

        List<Products> list2 = customer.getProducts();
        for (Products p : list2) {
            System.out.println(p);
        }
        System.out.println("Total sum: " + customer.getTotalSum());
        em.getTransaction().commit();
    }

    private void signIn() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input login");
            String login = scanner.nextLine();

            System.out.println("Input password");
            String password = scanner.nextLine();

            try {
                TypedQuery<Customer> query = em.createNamedQuery(CustomerDetails.GET_CUSTOMER_BY_LOGIN_AND_PASSWORD, Customer.class)
                        .setParameter("login", login)
                        .setParameter("password", password);
                customer = query.getSingleResult();
                System.out.println("User was found");
                purchaseGoods();
                return;

            } catch (NoResultException ex) {
                System.out.println("User was not found");
            }

            System.out.println("Do you want to try again? (yes, no)");
            String choice = scanner.nextLine();
            if (choice.equals("no")) break;
        }
    }

}
