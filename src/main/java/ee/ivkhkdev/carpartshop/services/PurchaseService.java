package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.helpers.FileHelper;
import ee.ivkhkdev.carpartshop.interfaces.IPurchaseService;
import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.model.PurchasedProduct;

import java.io.IOException;
import java.util.List;

public class PurchaseService implements IPurchaseService {
    private final List<PurchasedProduct> purchases;
    private final FileHelper<PurchasedProduct> fileHelper;
    private static final String PURCHASE_FILE = "purchases.dat";

    public PurchaseService() {
        fileHelper = new FileHelper<>();
        purchases = fileHelper.loadFromFile(PURCHASE_FILE);
    }

    @Override
    public void purchaseProduct(Customer customer, Product product) {
        purchases.add(new PurchasedProduct(customer, product));
    }

    @Override
    public List<PurchasedProduct> getPurchasedProducts() {
        return purchases;
    }

    public void savePurchases() throws IOException {
        fileHelper.saveToFile(PURCHASE_FILE, purchases);
    }
}
