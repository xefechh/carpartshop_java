package ee.ivkhkdev.phoneshop.inputs;

import ee.ivkhkdev.phoneshop.interfaces.Input;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String nextLine(){
        return scanner.nextLine();
    }

}
