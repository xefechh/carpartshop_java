package ee.ivkhkdev.carpartshop;

import ee.ivkhkdev.carpartshop.helpers.AppHelper;

import java.util.Scanner;

public class ShopApp {
    public ShopApp(Scanner scanner) {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppHelper appHelper = new AppHelper(scanner);
        appHelper.run();
        scanner.close();
    }
}
