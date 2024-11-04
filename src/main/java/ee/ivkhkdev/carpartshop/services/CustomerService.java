package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.helpers.FileHelper;
import ee.ivkhkdev.carpartshop.interfaces.ICustomerService;
import ee.ivkhkdev.carpartshop.model.Customer;

import java.io.IOException;
import java.util.List;

public class CustomerService implements ICustomerService {
    private final List<Customer> customers;
    private final FileHelper<Customer> fileHelper;
    private static final String CUSTOMER_FILE = "customers.dat";

    public CustomerService() {
        fileHelper = new FileHelper<>();
        customers = fileHelper.loadFromFile(CUSTOMER_FILE);
    }

    @Override
    public void addCustomer(String name) {
        customers.add(new Customer(name));
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void saveCustomers() throws IOException {
        fileHelper.saveToFile(CUSTOMER_FILE, customers);
    }
}
