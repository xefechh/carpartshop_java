package ee.ivkhkdev.phoneshop.helpers;

import ee.ivkhkdev.phoneshop.inputs.ConsoleInput;
import ee.ivkhkdev.phoneshop.model.Customer;
import ee.ivkhkdev.phoneshop.model.Product;
import ee.ivkhkdev.phoneshop.model.PurchasedProduct;
import ee.ivkhkdev.phoneshop.services.CustomerService;
import ee.ivkhkdev.phoneshop.services.ProductService;
import ee.ivkhkdev.phoneshop.services.PurchaseService;

import java.util.List;

public class PurchaseAppHelper {
    private final ConsoleInput input;
    private final CustomerService customerService;
    private final ProductService productService;
    private final PurchaseService purchaseService;

    public PurchaseAppHelper(ConsoleInput input, CustomerService customerService, ProductService productService, PurchaseService purchaseService) {
        this.input = input;
        this.customerService = customerService;
        this.productService = productService;
        this.purchaseService = purchaseService;
    }

    public void purchaseProduct() {
        CustomerAppHelper.listCustomers();
        System.out.print("Выберите номер клиента: ");
        int customerIndex = Integer.parseInt(input.nextLine()) - 1; // Use input.nextLine()

        PhoneAppHelper.listProducts();
        System.out.print("Выберите номер товара: ");
        int productIndex = Integer.parseInt(input.nextLine()) - 1; // Use input.nextLine()

        Customer customer = customerService.getCustomers().get(customerIndex);
        Product product = productService.getProducts().get(productIndex);

        purchaseService.purchaseProduct(customer, product);  // This will save the purchase

        System.out.println("Покупка совершена!");
    }


    public void listPurchasedProducts() {
        List<PurchasedProduct> purchasedProducts = purchaseService.getPurchasedProducts();
        if (purchasedProducts.isEmpty()) {
            System.out.println("Нет покупок.");
        } else {
            purchasedProducts.forEach(purchase ->
                    System.out.printf("Клиент: %s, Товар: %s, Цена: %.2f€%n",
                            purchase.getBuyer(), purchase.getName(), purchase.getPrice()));
        }
    }
}

