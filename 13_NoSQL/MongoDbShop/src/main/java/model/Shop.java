package model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    String name;
    List<Product>products;

    public Shop(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public Shop(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public void addProduct (Product product){
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
