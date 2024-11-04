package ee.ivkhkdev.carpartshop.services;

import ee.ivkhkdev.carpartshop.helpers.FileHelper;
import ee.ivkhkdev.carpartshop.interfaces.IProductService;
import ee.ivkhkdev.carpartshop.model.Product;

import java.io.IOException;
import java.util.List;

public class ProductService implements IProductService {
    private final List<Product> products;
    private final FileHelper<Product> fileHelper;
    private static final String PRODUCT_FILE = "products.dat";

    public ProductService() {
        fileHelper = new FileHelper<>();
        products = fileHelper.loadFromFile(PRODUCT_FILE);
    }

    @Override
    public void addProduct(String name, double price) {
        products.add(new Product(name, price));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    public void saveProducts() throws IOException {
        fileHelper.saveToFile(PRODUCT_FILE, products);
    }
}
