package ee.ivkhkdev.carpartshop;

import ee.ivkhkdev.carpartshop.helpers.AppHelper;
import ee.ivkhkdev.carpartshop.inputs.ConsoleInput;
import ee.ivkhkdev.carpartshop.interfaces.Input;

import java.util.Scanner;

public class ShopApp {
    public static void main(String[] args) {
        Input input = new ConsoleInput(new Scanner(System.in));
        AppHelper appHelper = new AppHelper(input);

        appHelper.run();
    }
}
