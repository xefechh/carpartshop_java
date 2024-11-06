package ee.ivkhkdev.phoneshop;

import ee.ivkhkdev.phoneshop.helpers.PurchaseAppHelper;
import ee.ivkhkdev.phoneshop.inputs.ConsoleInput;
import ee.ivkhkdev.phoneshop.interfaces.Input;
import ee.ivkhkdev.phoneshop.services.CustomerService;
import ee.ivkhkdev.phoneshop.services.ProductService;
import ee.ivkhkdev.phoneshop.services.PurchaseService;
import ee.ivkhkdev.phoneshop.helpers.CustomerAppHelper;
import ee.ivkhkdev.phoneshop.helpers.PhoneAppHelper;

public class PhoneShopApp {
    private final Input input;
    private final ProductService productService;
    private final CustomerService customerService;
    private final PurchaseService purchaseService;
    private final CustomerAppHelper customerAppHelper;
    private final PurchaseAppHelper purchaseAppHelper;
    private final PhoneAppHelper phoneAppHelper;


    public PhoneShopApp(ConsoleInput input,ProductService productService, CustomerService customerService, PurchaseService purchaseService, CustomerAppHelper customerAppHelper, PurchaseAppHelper purchaseAppHelper, PhoneAppHelper phoneAppHelper) {
        this.input = input;
        this.productService = productService;
        this.customerService = customerService;
        this.purchaseService = purchaseService;
        this.customerAppHelper = customerAppHelper;
        this.purchaseAppHelper = purchaseAppHelper;
        this.phoneAppHelper = phoneAppHelper;
    }



    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("""
            \n
            0. Выйти
            1. Добавить товар
            2. Список товаров
            3. Добавить клиента
            4. Список клиентов
            5. Купить товар
            6. Список покупок
            """);
            System.out.print("Выберите номер задачи: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 0 -> running = false;
                case 1 -> phoneAppHelper.addProduct();
                case 2 -> phoneAppHelper.listProducts();
                case 3 -> customerAppHelper.addCustomer();
                case 4 -> customerAppHelper.listCustomers();
                case 5 -> purchaseAppHelper.purchaseProduct();
                case 6 -> purchaseAppHelper.listPurchasedProducts();
                default -> System.out.println("Некорректный выбор!");
            }
        }

        saveData();
    }


    private void saveData() {
        productService.saveProducts();
        customerService.saveCustomers();
        purchaseService.savePurchases();
    }
}
