import domain.Prices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        loadPrices();

        System.out.println("Sign in or sign up? (1/2)");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String choice = reader.readLine();
            switch (choice){
                case "1": new Authorization();
                case "2": new Registration();
                default:
                    System.out.println("Are you ok? You were supposed to choose" +
                            " two options: 1 or 2, but you chose invalid value " + choice);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadPrices() {

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
