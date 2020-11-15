package registr;

import domain.Customer;

import javax.persistence.EntityManager;

public interface RegistrationBusinessLogic {
    Customer getSignedUpCustomer();
}
