package nl.avans.ivh11.DemoApplication.controller.api;

import nl.avans.ivh11.DemoApplication.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
public class ApiProductController {

    private final Logger logger = LoggerFactory.getLogger(ApiProductController.class);

    private ArrayList<Product> products = new ArrayList<>();

    @GetMapping(value = "/")
    ArrayList<Product> getProductsAsJSON() {
        logger.trace("getProducts was called.");
        return products;
    }

    @PostMapping(value = "/")
    ArrayList<Product> createProductAsJSON(@RequestBody Product product) {
        logger.trace("createProduct was called.");
        products.add(product);
        return products;

    }

}
