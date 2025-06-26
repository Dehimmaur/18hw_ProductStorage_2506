package product.model;

import product.dao.ProductStorageImpl;

import java.security.PrivateKey;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class Product {
    private final String id;
    private String name;
    private final String category;
    private double price;
    private int quantity;

    private final Category categoryType;


    public Product(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;

        this.price = price;
        this.quantity = quantity;

        this.categoryType = Category.takeCategory(category);
        this.category = categoryType.getCategory();
    }
/*
    public Product(String id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

 */



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return categoryType.getCategory();
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + categoryType.category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    public enum Category{
        MILK_FOOD("Milk food", "Молочные продукты", "מוצרי חלב"),
        BREAD("Breads", "Хлеб", "לחם"),
        SWEETS("Sweets", "Сладости", "ממתקים"),
        MEAT("Meat", "Мясо", "בשר");

        private final String category;
        private final String categoryRu;
        private final String categoryHe;

        Category(String category, String categoryRu, String categoryHe) {
            this.category = category;
            this.categoryRu = categoryRu;
            this.categoryHe = categoryHe;
        }

        private String getCategory() {
            return category;
        }

        private static Category takeCategory(String name) {
            for (Category c : values()) {
                if (c.category.equalsIgnoreCase(name) || c.categoryRu.equalsIgnoreCase(name) || c.categoryHe.equals(name)) {
                    return c;
                }
            }
            throw new IllegalArgumentException("Неизвестная категория: " + name);
        }
    }
}
