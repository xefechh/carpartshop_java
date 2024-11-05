package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.model.PurchasedProduct;
import ee.ivkhkdev.carpartshop.repositories.Storage;
import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;

import java.util.List;

public class PurchaseService {

    private final Storage<PurchasedProduct> purchaseStorage;

    public PurchaseService() {
        this.purchaseStorage = new Storage<>("purchases.dat");
    }

    public void purchaseProduct(Customer customer, Product product) {
        PurchasedProduct purchase = new PurchasedProduct(customer, product);
        purchaseStorage.save(purchase);  // Save purchase using Storage
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchaseStorage.load();  // Load purchases from file
    }

    public void savePurchases() {
        // If you want to batch save purchases, you can use this method
        // purchaseStorage.saveAll(purchaseList);
    }
}
