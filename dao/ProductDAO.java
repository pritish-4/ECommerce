package dao;

import model.Product;
import java.util.List;

public interface ProductDAO {
    void save(Product product);
    List<Product> findAll();
    Product findById(String id);
}
