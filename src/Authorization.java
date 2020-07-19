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

    private String login;
    private String password;

    public Authorization() {
        signIn();
    }


    private void purchaseGoods() {
        em.getTransaction().begin();
        customer = em.find();

        System.out.println("List of goods");
        Query query = em.createNamedQuery(Prices.GET_PRODUCT_NAME_AND_PRICE);
        List<Object[]> list = query.getResultList();
        for (Object[] p : list) {
            System.out.print(p[0] + " ");
            System.out.print(p[1] + "\n");
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
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

            System.out.println("Do u want to stop shopping? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equals("yes")) break;
        }

        Query query1 = em.createNativeQuery(Customer.GET_LIST_OF_GOODS, Object[].class);
        List<Object[]> list1 = query.getResultList();
        for (Object[] p : list1) {
            System.out.print(p[0] + " ");
            System.out.print(p[1] + " Total sum: ");
            System.out.print(p[2] );
        }
        em.getTransaction().commit();
    }

    private void signIn() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input login");
            String login = scanner.nextLine();

            System.out.println("Input password");
            String password = scanner.nextLine();
            TypedQuery<Object[]> query = em.createNamedQuery(CustomerDetails.GET_LOGIN_AND_PASSWORD, Object[].class)
                    .setParameter("login", login)
                    .setParameter("password", password);
            Object[] temp = query.getSingleResult();
            if (temp == null) System.out.println("User was not found");
            else{
                login = (String) temp[0];
                password = (String) temp[1];//getCustomer
                purchaseGoods();
            }
            System.out.println("Do you want to try again? (yes, no)");
            String choice = scanner.nextLine();
            if (choice.equals("no")) break;
        }
    }

}
