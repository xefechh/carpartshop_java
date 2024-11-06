package ee.ivkhkdev.phoneshop;

import ee.ivkhkdev.phoneshop.helpers.CustomerAppHelper;
import ee.ivkhkdev.phoneshop.helpers.PhoneAppHelper;
import ee.ivkhkdev.phoneshop.helpers.PurchaseAppHelper;
import ee.ivkhkdev.phoneshop.inputs.ConsoleInput;
import ee.ivkhkdev.phoneshop.interfaces.Repository;
import ee.ivkhkdev.phoneshop.model.Customer;
import ee.ivkhkdev.phoneshop.model.Product;
import ee.ivkhkdev.phoneshop.model.PurchasedProduct;
import ee.ivkhkdev.phoneshop.repositories.Storage;
import ee.ivkhkdev.phoneshop.services.CustomerService;
import ee.ivkhkdev.phoneshop.services.ProductService;
import ee.ivkhkdev.phoneshop.services.PurchaseService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput(new Scanner(System.in));

        Repository<Customer> customerStorage = new Storage<>("customers");
        Storage<Product> productStorage = new Storage<>("phones");
        Storage<PurchasedProduct> purchasedProductStorage = new Storage<>("purchasedPhones");

        ProductService productService = new ProductService(productStorage);
        CustomerService customerService = new CustomerService(customerStorage);
        PurchaseService purchaseService = new PurchaseService(purchasedProductStorage);
        CustomerAppHelper customerAppHelper = new CustomerAppHelper(input, customerService);
        PhoneAppHelper phoneAppHelper = new PhoneAppHelper(input, productService);
        PurchaseAppHelper purchaseAppHelper = new PurchaseAppHelper(input, customerService, productService, purchaseService);

        PhoneShopApp app = new PhoneShopApp(input, productService, customerService, purchaseService,customerAppHelper, purchaseAppHelper, phoneAppHelper);
        app.run();
    }
}
