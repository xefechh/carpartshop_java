package ee.ivkhkdev.phoneshop.model;

import java.io.Serializable;

public record Product(String name, float price) implements Serializable { }

