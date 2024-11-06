package ee.ivkhkdev.phoneshop.services;

import ee.ivkhkdev.phoneshop.model.PurchasedProduct;
import ee.ivkhkdev.phoneshop.repositories.Storage;
import ee.ivkhkdev.phoneshop.model.Customer;
import ee.ivkhkdev.phoneshop.model.Product;

import java.util.List;

public class PurchaseService {

    private final Storage<PurchasedProduct> purchaseStorage;

    public PurchaseService(Storage<PurchasedProduct> purchaseStorage) {
        this.purchaseStorage = purchaseStorage;
    }

    public void purchaseProduct(Customer customer, Product product) {
        PurchasedProduct purchase = new PurchasedProduct(customer, product);
        purchaseStorage.save(purchase);  // Save purchase using Storage
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchaseStorage.load();  // Load purchases from file
    }

    public void savePurchases() {
        // Save purchases in batch if required
    }
}
