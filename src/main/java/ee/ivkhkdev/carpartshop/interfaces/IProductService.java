package ee.ivkhkdev.carpartshop.interfaces;

import ee.ivkhkdev.carpartshop.model.Product;

import java.util.List;

public interface IProductService {
    void addProduct(String name, double price);
    List<Product> getProducts();
}
