package ee.ivkhkdev.phoneshop.helpers;

import ee.ivkhkdev.phoneshop.interfaces.Input;
import ee.ivkhkdev.phoneshop.services.ProductService;
import ee.ivkhkdev.phoneshop.model.Product;

import java.util.List;

public class PhoneAppHelper {
    private static Input input;
    private static ProductService productService;

    public PhoneAppHelper(Input input, ProductService productService) {
        PhoneAppHelper.input = input;
        PhoneAppHelper.productService = productService;
    }

    public static void addProduct() {
        System.out.print("Введите название товара: ");
        String name = input.nextLine(); // Use input.nextLine()

        System.out.print("Введите цену товара: ");
        float price = Float.parseFloat(input.nextLine()); // Use input.nextLine()

        if (price <= 0) {
            System.out.println("Цена должна быть больше нуля. Попробуйте снова.");
            return;
        }

        productService.addProduct(name, price);
        System.out.println("Товар добавлен!");
    }


    public static void listProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("Нет товаров.");
        } else {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                System.out.printf("%d. %s - %.2f€%n", i + 1, product.name(), product.price());
            }
        }
    }
}