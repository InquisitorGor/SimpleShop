package depricated;

import domain.Prices;
import registr.Registration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Sign in or sign up? (1/2)");
        Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch (choice){
                case "1": {
                    new Authorization();
                    break;
                }
                case "2": {
                    new Registration();
                    break;
                }
                default:
                    System.out.println("Are you ok? You were supposed to choose" +
                            " two options: 1 or 2, but you chose invalid value " + choice);
            }


    }

    static void loadPrices() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Prices beer = new Prices();
        beer.setProductName("Beer");
        beer.setPrice(200);
        em.persist(beer);

        Prices apple = new Prices();
        apple.setProductName("Apple");
        apple.setPrice(6);
        em.persist(apple);

        Prices carrot = new Prices();
        carrot.setProductName("Carrot");
        carrot.setPrice(15);
        em.persist(carrot);

        Prices pizza = new Prices();
        pizza.setProductName("Pizza");
        pizza.setPrice(150);
        em.persist(pizza);

        Prices hamburger = new Prices();
        hamburger.setProductName("Hamburger");
        hamburger.setPrice(90);
        em.persist(hamburger);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
