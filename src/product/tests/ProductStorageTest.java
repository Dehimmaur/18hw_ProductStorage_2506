package product.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.dao.ProductStorage;
import product.dao.ProductStorageImpl;
import product.model.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductStorageTest {
    private ProductStorage storage;
    private Product[] testStorage;

    @BeforeEach
    void setUp() {
        storage = new ProductStorageImpl(5);
        testStorage = new Product[3];
        testStorage[0] = new Product("id10001001", "Milk", "Milk products", 7.9, 500);
        testStorage[1] = new Product("id20001001", "Bread Darnitskiy", "Breads", 15.8, 500);
        testStorage[2] = new Product("id30001001", "Chocolate Dubai", "Sweets", 21.4, 100);
        for (Product product : testStorage) {
            storage.addProduct(product);
        }
    }

    @Test
    void addProduct() {
        assertFalse(storage.addProduct(null));
        Product newProduct1 = new Product("id30001002", "Chupa-Chups", "Sweets", 3, 2000);
        Product newProduct2 = new Product("id30001003", "Ritter Sport", "Sweets", 15, 300);
        Product newProduct3 = new Product("id40001001", "Farm Chicken", "Meat", 39 , 50);

        assertTrue(storage.addProduct(newProduct1));
        assertTrue(storage.addProduct(newProduct3));
        assertTrue(storage.addProduct(newProduct3));
        assertFalse(storage.addProduct(newProduct2));

        Product newProduct4 = new Product("id40001001", "Farm Chicken", "Meat", 39 , -50);
        Product newProduct5 = new Product("id40001001", "Farm Chicken", "Meat", 39 , 0);
        assertFalse(storage.addProduct(newProduct4));
        assertFalse(storage.addProduct(newProduct5));

        storage.removeProduct("id40001001");
        assertTrue(storage.addProduct(newProduct5));
    }

    @Test
    void removeProduct() {
        assertNull(storage.removeProduct(null));
        assertEquals(testStorage[0], storage.removeProduct("id10001001"));
        assertEquals(testStorage[1], storage.removeProduct("id20001001"));
        assertEquals(testStorage[2], storage.removeProduct("id30001001"));
    }

    @Test
    void findProductById() {
        assertNull(storage.findProductById(null));
        assertEquals(testStorage[0], storage.findProductById(testStorage[0].getId()));
        assertEquals(testStorage[1], storage.findProductById(testStorage[1].getId()));
        assertNull(storage.findProductById("id100001001"));
    }

    @Test
    void findProductsByName() {
        assertNull(storage.findProductsByName(null));

        Product newProduct1 = new Product("id10001002", "Milk", "Milk products", 8.5, 500);
        Product newProduct2 = new Product("id10001003", "Milk", "Milk products", 12.5, 200);

        assertTrue(storage.addProduct(newProduct1));
        assertTrue(storage.addProduct(newProduct2));

        Product[] actual = storage.findProductsByName(newProduct1.getName());
        Product[] expected ={testStorage[0], newProduct1, newProduct2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findProductsByCategory() {
        assertNull(storage.findProductsByCategory(null));

        Product newProduct1 = new Product("id10001002", "Milk", "Milk products", 8.5, 500);
        Product newProduct2 = new Product("id10001003", "Milk", "Milk products", 12.5, 200);
        assertTrue(storage.addProduct(newProduct1));
        assertTrue(storage.addProduct(newProduct2));

        Product[] actual = storage.findProductsByCategory(newProduct2.getCategory());
        Product[] expected ={testStorage[0], newProduct1, newProduct2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findProductsByPrice() {

        Product newProduct1 = new Product("id10001002", "Milk", "Milk products", 8.5, 500);
        Product newProduct2 = new Product("id10001003", "Milk", "Milk products", 12.5, 200);
        double minPrice = 10.0;
        assertTrue(storage.addProduct(newProduct1));
        assertTrue(storage.addProduct(newProduct2));

        Product[] actual = storage.findProductsByPrice(minPrice);
        Product[] expected ={testStorage[1], testStorage[2], newProduct2};

        assertArrayEquals(expected, actual);
    }

    @Test
    void findProductsByQuantity() {
        Product newProduct1 = new Product("id10001002", "Milk", "Milk products", 8.5, 1000);
        Product newProduct2 = new Product("id10001003", "Milk", "Milk products", 12.5, 2000);
        int minQuantity = 500;
        int maxQuantity = 1000;
        assertTrue(storage.addProduct(newProduct1));
        assertTrue(storage.addProduct(newProduct2));

        Product[] actual = storage.findProductsByQuantity(minQuantity, maxQuantity);
        Product[] expected ={testStorage[0], testStorage[1], newProduct1};

        assertArrayEquals(expected, actual);
    }

}