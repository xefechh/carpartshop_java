package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.interfaces.Repository;
import ee.ivkhkdev.carpartshop.model.Customer;

import java.util.List;

public class CustomerService {

    private final Repository<Customer> customerStorage;

    // Constructor now takes Repository<Customer> instead of Storage
    public CustomerService(Repository<Customer> customerStorage) {
        this.customerStorage = customerStorage;
    }

    public void addCustomer(String name) {
        Customer customer = new Customer(name);
        customerStorage.save(customer);  // Save customer using the repository
    }

    public List<Customer> getCustomers() {
        return customerStorage.load();  // Load customers from repository
    }

    public void saveCustomers() {
        // Save logic if needed (though not necessary if saving is done on a per-operation basis)
    }
}
