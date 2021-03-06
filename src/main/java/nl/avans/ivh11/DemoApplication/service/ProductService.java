package nl.avans.ivh11.demoapplication.service;

import nl.avans.ivh11.demoapplication.domain.Product;
import nl.avans.ivh11.demoapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository repository) {
        this.productRepository = repository;
    }

    public ArrayList<Product> getProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }
}
