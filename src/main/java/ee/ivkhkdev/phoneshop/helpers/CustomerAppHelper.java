package ee.ivkhkdev.phoneshop.helpers;

import ee.ivkhkdev.phoneshop.inputs.ConsoleInput;
import ee.ivkhkdev.phoneshop.model.Customer;
import ee.ivkhkdev.phoneshop.services.CustomerService;

import java.util.List;

public class CustomerAppHelper {
    private final ConsoleInput input;
    private static CustomerService customerService;

    public CustomerAppHelper(ConsoleInput input, CustomerService customerService) {
        this.input = input;
        CustomerAppHelper.customerService = customerService;
    }

    public void addCustomer() {
        System.out.print("Введите имя клиента: ");
        String name = input.nextLine();
        customerService.addCustomer(name);
        System.out.println("Клиент добавлен!");
    }

    public static void listCustomers() {
        List<Customer> customers = customerService.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("Нет клиентов.");
        } else {
            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);
                System.out.printf("%d. %s%n", i + 1, customer.name());
            }
        }
    }
}

