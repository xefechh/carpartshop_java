package ee.ivkhkdev.phoneshop.model;

import java.io.Serializable;

public class PurchasedProduct implements Serializable {
    private final String name;
    private final float price;
    private final String buyer;

    public PurchasedProduct(Customer customer, Product product) {
        this.name = product.name();  // using the getter for the 'name' field of Product
        this.price = product.price();  // using the getter for the 'price' field of Product
        this.buyer = customer.name();
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getBuyer() {
        return buyer;
    }
}
