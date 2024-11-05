package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.repositories.Storage;

import java.util.List;

public class CustomerService {

    private final Storage<Customer> customerStorage;

    public CustomerService() {
        this.customerStorage = new Storage<>("customers.dat");
    }

    public void addCustomer(String name) {
        Customer customer = new Customer(name);
        customerStorage.save(customer);  // Save customer using Storage
    }

    public List<Customer> getCustomers() {
        return customerStorage.load();  // Load customers from file
    }

    public void saveCustomers() {
        // Optionally save all at once if needed, but in this case each save happens individually
    }
}
