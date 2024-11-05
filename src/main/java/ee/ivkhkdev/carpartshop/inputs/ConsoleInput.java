package ee.ivkhkdev.carpartshop.inputs;

import ee.ivkhkdev.carpartshop.interfaces.Input;

import java.util.HashSet;
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
