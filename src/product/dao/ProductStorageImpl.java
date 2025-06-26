package product.dao;

import product.model.Product;

import java.sql.Driver;
import java.util.function.Predicate;

public class ProductStorageImpl implements ProductStorage{
    private Product[] products;
    private int size;

    public ProductStorageImpl(int capacity) {products = new Product[capacity];}

    @Override
    public boolean addProduct(Product product) {
        if (product == null) { return false;}
        if (findProductById(product.getId()) != null) {
            for (int i = 0; i < size; i++) {
                if (product.getId().equals(products[i].getId())){
                    if (product.getQuantity() <= 0) {return false;}
                    products[i].setQuantity(products[i].getQuantity() + product.getQuantity());
                    return true;
                }
            }
        }
        if (size == products.length) {
            return false;
        }
        products[size++] = product;
        return true;
    }

    @Override
    public Product removeProduct(String id) {
        if (id == null) {return null;}
        for (int i = 0; i < size; i++) {
            if (id.equals(products[i].getId())) {
                Product removeProduct = products[i];
                products[i] = products[--size];
                products[size] = null;
                return removeProduct;
            }
        }
        return null;
    }

    @Override
    public Product findProductById(String id) {
        if (id == null) {return null;}
        for (int i = 0; i < size; i++) {
            if (id.equals(products[i].getId())){
                return products[i];
            }
        }
        return null;
    }

    public void printStorage() {
        for (Product product : products) {
            System.out.println(product);
        }
    }



    @Override
    public Product[] findProductsByPredicate(Predicate<Product> predicate) {
        int count = 0;
        for (Product product : products){
            if (predicate.test(product)){
                count++;
            }
        }
        Product[] resProducts = new Product[count];
        int j = 0;
        for (Product product : products){
            if (predicate.test(product)){
                resProducts[j++] = product;
            }
        }
        return resProducts;
    }
}
