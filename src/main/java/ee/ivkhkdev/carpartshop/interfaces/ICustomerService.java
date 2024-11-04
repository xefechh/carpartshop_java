package ee.ivkhkdev.carpartshop.interfaces;

import ee.ivkhkdev.carpartshop.model.Customer;

import java.util.List;

public interface ICustomerService {
    void addCustomer(String name);
    List<Customer> getCustomers();
}
