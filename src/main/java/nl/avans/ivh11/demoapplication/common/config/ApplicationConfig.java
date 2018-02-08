package nl.avans.ivh11.demoapplication.common.config;

import nl.avans.ivh11.demoapplication.domain.Order;
import nl.avans.ivh11.demoapplication.domain.Product;
import nl.avans.ivh11.demoapplication.domain.ProductCatalog;
import nl.avans.ivh11.demoapplication.repository.OrderRepository;
import nl.avans.ivh11.demoapplication.repository.ProductCatalogRepository;
import nl.avans.ivh11.demoapplication.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCatalogRepository productCatalogRepository;

    public ApplicationConfig(
            OrderRepository orderRepo,
            ProductRepository productRepo,
            ProductCatalogRepository productCatalogRepository) {
        this.orderRepository = orderRepo;
        this.productRepository = productRepo;
        this.productCatalogRepository = productCatalogRepository;
    }

    @PostConstruct
    public void createDummyContents() {
        logger.debug("createDummyContents");

        Order order = new Order();
        logger.debug("Saving empty order");
        order = orderRepository.save(order);

        logger.debug("Creating productCatalog");
        ProductCatalog productCatalog = new ProductCatalog();
        logger.debug("Saving productCatalog");
        productCatalog = productCatalogRepository.save(productCatalog);

        logger.debug("Creating product");
        Product product = new Product("Borrelnootjes", "Zakje borrelnootjes", 10);
        product = this.productRepository.save(product);

        logger.debug("Product = " + product.toString());

        productCatalog.add(product, 2);

//        order.add(product);
    }
}
