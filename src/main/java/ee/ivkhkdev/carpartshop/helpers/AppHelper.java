package ee.ivkhkdev.carpartshop.helpers;

import ee.ivkhkdev.carpartshop.services.CustomerService;
import ee.ivkhkdev.carpartshop.services.ProductService;
import ee.ivkhkdev.carpartshop.services.PurchaseService;
import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;

import java.io.IOException;
import java.util.Scanner;

public class AppHelper {
    private final Scanner scanner;
    private final ProductService productService;
    private final CustomerService customerService;
    private final PurchaseService purchaseService;

    public AppHelper(Scanner scanner) {
        this.scanner = scanner;
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
            int choice = Integer.parseInt(scanner.nextLine());

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
        String name = scanner.nextLine();
        System.out.print("Введите цену товара: ");
        double price = Double.parseDouble(scanner.nextLine());
        productService.addProduct(name, price);
        System.out.println("Товар добавлен!");
    }

    private void listProducts() {
        productService.getProducts().forEach(product ->
                System.out.printf("%s - %.2f€%n", product.name(), product.price()));
    }

    private void addCustomer() {
        System.out.print("Введите имя клиента: ");
        String name = scanner.nextLine();
        customerService.addCustomer(name);
        System.out.println("Клиент добавлен!");
    }

    private void listCustomers() {
        customerService.getCustomers().forEach(customer ->
                System.out.println(customer.name()));
    }

    private void purchaseProduct() {
        listCustomers();
        System.out.print("Выберите номер клиента: ");
        int customerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        listProducts();
        System.out.print("Выберите номер товара: ");
        int productIndex = Integer.parseInt(scanner.nextLine()) - 1;

        Customer customer = customerService.getCustomers().get(customerIndex);
        Product product = productService.getProducts().get(productIndex);
        purchaseService.purchaseProduct(customer, product);
        System.out.println("Покупка совершена!");
    }

    private void listPurchasedProducts() {
        purchaseService.getPurchasedProducts().forEach(purchase ->
                System.out.printf("Клиент: %s, Товар: %s%n",
                        purchase.customer().name(), purchase.product().name()));
    }

    private void saveData() {
        try {
            productService.saveProducts();
            customerService.saveCustomers();
            purchaseService.savePurchases();
        } catch (IOException e) {
            System.out.println("Ошибка сохранения данных: " + e.getMessage());
        }
    }
}
