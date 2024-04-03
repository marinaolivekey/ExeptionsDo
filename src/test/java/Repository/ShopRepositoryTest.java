package Repository;

import Domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShopRepositoryTest {

    Product item1 = new Product(1, "bread", 50);
    Product item2 = new Product(2, "milk", 100);
    Product item3 = new Product(3, "egg", 150);
    Product item4 = new Product(4, "butter", 200);

    @Test
    public void add() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product[] expected = {item1, item2, item3, item4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findAll() {
        ShopRepository repo = new ShopRepository();
        repo.add(item3);

        Product[] expected = {item3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void findById() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product expected = item3;
        Product actual = repo.findById(3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findByIdNotExist() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        //Product expected = null;
        Product actual = repo.findById(5);

        Assertions.assertEquals(null, actual);
    }

    @Test
    void findByIdNotExistAndNegative() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        //Product expected = null;
        Product actual = repo.findById(-1);

        Assertions.assertEquals(null, actual);
    }


    @Test
    void removeOne() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        repo.remove(item3.getId());

        Product[] expected = {item1, item2, item4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeTwo() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        repo.remove(item3.getId());
        repo.remove(item4.getId());

        Product[] expected = {item1, item2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeAll() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        repo.remove(item3.getId());
        repo.remove(item4.getId());
        repo.remove(item2.getId());
        repo.remove(item1.getId());

        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void removeIdNotExist() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(6);
        });
    }

    @Test
    void removeIdNotExistAndNegative() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(-6);
        });
    }


}