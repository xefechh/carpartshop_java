package ee.ivkhkdev.carpartshop.model;

import java.io.Serializable;

public record PurchasedProduct(Customer customer, Product product) implements Serializable {}
