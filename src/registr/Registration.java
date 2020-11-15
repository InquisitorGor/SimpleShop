package registr;

import domain.Customer;
import domain.CustomerDetails;


import java.util.Scanner;

public class Registration implements RegistrationBusinessLogic {

    private Customer customer;

    @Override
    public Customer getSignedUpCustomer() {
        Scanner scanner = new Scanner(System.in);
        customer = new Customer();

        System.out.println("Input first name");
        String firstName = scanner.nextLine();

        System.out.println("Input last name");
        String lastName = scanner.nextLine();

        System.out.println("Input login");
        String login = scanner.nextLine();

        System.out.println("Input password");
        String password = scanner.nextLine();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setLogin(login);
        customerDetails.setPassword(password);
        customer.setCustomerDetails(customerDetails);

        return customer;
    }


}
