package ee.ivkhkdev.carpartshop;

import ee.ivkhkdev.carpartshop.helpers.AppHelper;
import ee.ivkhkdev.carpartshop.inputs.ConsoleInput;
import ee.ivkhkdev.carpartshop.interfaces.Input;
import ee.ivkhkdev.carpartshop.interfaces.Repository;
import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.model.PurchasedProduct;
import ee.ivkhkdev.carpartshop.repositories.Storage;
import ee.ivkhkdev.carpartshop.services.CustomerService;
import ee.ivkhkdev.carpartshop.services.ProductService;
import ee.ivkhkdev.carpartshop.services.PurchaseService;

import java.util.Scanner;

public class ShopApp {
    public static void main(String[] args) {
        Input input = new ConsoleInput(new Scanner(System.in));

        // Initialize repositories
        Repository<Customer> customerStorage = new Storage<>("customers.dat");
        Repository<Product> productStorage = new Storage<>("products.dat");
        Repository<PurchasedProduct> purchasedProductStorage = new Storage<>("purchasedProducts.dat");

        // Initialize services with appropriate repositories
        ProductService productService = new ProductService((Storage<Product>) productStorage);
        CustomerService customerService = new CustomerService(customerStorage); // No casting needed
        PurchaseService purchaseService = new PurchaseService((Storage<PurchasedProduct>) purchasedProductStorage);

        // Initialize the app helper
        AppHelper appHelper = new AppHelper(input, productService, customerService, purchaseService);

        // Run the application
        appHelper.run();
    }
}
