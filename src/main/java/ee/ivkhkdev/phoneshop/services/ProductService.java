package ee.ivkhkdev.phoneshop.services;

import ee.ivkhkdev.phoneshop.model.Product;
import ee.ivkhkdev.phoneshop.repositories.Storage;

import java.util.List;

public class ProductService {

    private final Storage<Product> productStorage;

    public ProductService(Storage<Product> productStorage) {
        this.productStorage = productStorage;
    }

    public void addProduct(String name, float price) {
        Product product = new Product(name, price);
        productStorage.save(product);  // Save product using Storage
    }

    public List<Product> getProducts() {
        return productStorage.load();  // Load products from file
    }

    public void saveProducts() {
        // Optionally save all at once if needed
    }
}

