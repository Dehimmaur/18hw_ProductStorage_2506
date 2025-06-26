package product.dao;

import product.model.Product;

import java.util.function.Predicate;

public interface ProductStorage {

    boolean addProduct(Product product);

    Product removeProduct(String id);

    Product findProductById(String id);

    default Product[] findProductsByName(String name) {
        if (name == null) {return null;}
        return findProductsByPredicate(c -> name.equals(c.getName()));
    }

    default Product[] findProductsByCategory(String category) {
        if (category == null) {return null;}
        return findProductsByPredicate(c -> category.equals(c.getCategory()));
    }

    default Product[] findProductsByPrice(double min) {
        return findProductsByPredicate(c -> c.getPrice() >= min);
    }

    default Product[] findProductsByQuantity(int min, int max){
        return findProductsByPredicate(c -> c.getQuantity() >= min && c.getQuantity() <= max);
    }

    Product[] findProductsByPredicate(Predicate<Product> predicate);
}
