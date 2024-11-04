package ee.ivkhkdev.carpartshop.interfaces;

import ee.ivkhkdev.carpartshop.model.Customer;
import ee.ivkhkdev.carpartshop.model.Product;
import ee.ivkhkdev.carpartshop.model.PurchasedProduct;

import java.util.List;

public interface IPurchaseService {
    void purchaseProduct(Customer customer, Product product);
    List<PurchasedProduct> getPurchasedProducts();
}
