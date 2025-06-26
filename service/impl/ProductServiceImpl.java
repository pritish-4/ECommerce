package service.impl;

import dao.ProductDAO;
import dao.daoImpl.ProductDAOImpl;
import model.Product;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void addProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        return productDAO.findById(productId);
    }
}
