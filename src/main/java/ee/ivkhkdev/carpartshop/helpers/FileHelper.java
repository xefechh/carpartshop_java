package ee.ivkhkdev.carpartshop.helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper<T> {

    public void saveToFile(String filePath, List<T> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
