package ee.ivkhkdev.carpartshop.helpers;

import ee.ivkhkdev.carpartshop.interfaces.Input;
import ee.ivkhkdev.carpartshop.model.PurchasedProduct;
import ee.ivkhkdev.carpartshop.services.CustomerService;
import ee.ivkhkdev.carpartshop.services.ProductService;
import ee.ivkhkdev.carpartshop.services.PurchaseService;
import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;

import java.io.IOException;
import java.util.List;

public class AppHelper {
    private final Input input;  // The input interface is used for reading input
    private final ProductService productService;
    private final CustomerService customerService;
    private final PurchaseService purchaseService;

    public AppHelper(Input input) {
        this.input = input;
        this.productService = new ProductService();
        this.customerService = new CustomerService();
        this.purchaseService = new PurchaseService();
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("""
            0. Выйти
            1. Добавить товар
            2. Список товаров
            3. Добавить клиента
            4. Список клиентов
            5. Купить товар
            6. Список покупок
            """);
            System.out.print("Выберите номер задачи: ");
            int choice = Integer.parseInt(input.nextLine()); // Use input.nextLine()

            switch (choice) {
                case 0 -> running = false;
                case 1 -> addProduct();
                case 2 -> listProducts();
                case 3 -> addCustomer();
                case 4 -> listCustomers();
                case 5 -> purchaseProduct();
                case 6 -> listPurchasedProducts();
                default -> System.out.println("Некорректный выбор!");
            }
        }

        saveData();
    }

    private void addProduct() {
        System.out.print("Введите название товара: ");
        String name = input.nextLine(); // Use input.nextLine()

        System.out.print("Введите цену товара: ");
        float price = Float.parseFloat(input.nextLine()); // Use input.nextLine()

        if (price <= 0) {
            System.out.println("Цена должна быть больше нуля. Попробуйте снова.");
            return;
        }

        productService.addProduct(name, price);
        System.out.println("Товар добавлен!");
    }


    private void listProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("Нет товаров.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("%d. %s - %.2f€%n", i + 1, product.name(), product.price());
            }
        }
    }


    private void addCustomer() {
        System.out.print("Введите имя клиента: ");
        String name = input.nextLine(); // Use input.nextLine()
        customerService.addCustomer(name);
        System.out.println("Клиент добавлен!");
    }

    private void listCustomers() {
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


    private void purchaseProduct() {
        listCustomers();
        System.out.print("Выберите номер клиента: ");
        int customerIndex = Integer.parseInt(input.nextLine()) - 1; // Use input.nextLine()

        listProducts();
        System.out.print("Выберите номер товара: ");
        int productIndex = Integer.parseInt(input.nextLine()) - 1; // Use input.nextLine()

        Customer customer = customerService.getCustomers().get(customerIndex);
        Product product = productService.getProducts().get(productIndex);

        purchaseService.purchaseProduct(customer, product);  // This will save the purchase

        System.out.println("Покупка совершена!");
    }


    private void listPurchasedProducts() {
        List<PurchasedProduct> purchasedProducts = purchaseService.getPurchasedProducts();
        if (purchasedProducts.isEmpty()) {
            System.out.println("Нет покупок.");
        } else {
            purchasedProducts.forEach(purchase ->
                    System.out.printf("Клиент: %s, Товар: %s, Цена: %.2f€%n",
                            purchase.getBuyer(), purchase.getName(), purchase.getPrice()));
        }
    }


    private void saveData() {
        productService.saveProducts();
        customerService.saveCustomers();
        purchaseService.savePurchases();
    }
}
