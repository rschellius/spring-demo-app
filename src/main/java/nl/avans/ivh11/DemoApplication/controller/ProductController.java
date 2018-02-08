package nl.avans.ivh11.demoapplication.controller;

import nl.avans.ivh11.demoapplication.common.exception.ProductNotFoundException;
import nl.avans.ivh11.demoapplication.domain.Product;
import nl.avans.ivh11.demoapplication.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private ArrayList<Product> products = new ArrayList<>();

    // Views constants
    private final String VIEW_LIST_PRODUCTS = "views/product/list";
    private final String VIEW_CREATE_PRODUCT = "views/product/create";
    private final String VIEW_READ_PRODUCT = "views/product/read";

    @Autowired
    private final ProductService productService;

    // Constructor with Dependency Injection
    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    public String listProducts(
            @RequestParam(value="category", required=false, defaultValue="all") String category,
            @RequestParam(value="size", required=false, defaultValue="10") String size,
            Model model) {

        logger.debug("listProducts called.");
        Iterable<Product> products = productService.getProducts();

        model.addAttribute("category", category);
        model.addAttribute("size", size);
        model.addAttribute("products", products);
        return VIEW_LIST_PRODUCTS;
    }

    @GetMapping("{id}")
    public ModelAndView viewProduct(@PathVariable("id") Product product)
            throws ProductNotFoundException {
        if(null == product) {
            throw new ProductNotFoundException("Product niet gevonden!");
        }
        return new ModelAndView(VIEW_READ_PRODUCT, "product", product);
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String showCreateProductForm(final Product product, final ModelMap model) {
        logger.debug("showCreateProductForm");
        return VIEW_CREATE_PRODUCT;
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView validateAndSaveProduct(
            @Valid Product product,
            final BindingResult bindingResult,
            RedirectAttributes redirect) {

        logger.debug("validateAndSaveProduct - adding product " + product.getName());
        if (bindingResult.hasErrors()) {
            logger.debug("validateAndSaveProduct - not added, bindingResult.hasErrors");
            return new ModelAndView(VIEW_CREATE_PRODUCT, "formErrors", bindingResult.getAllErrors());
        }

        product = productService.createProduct(product);

        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
        return new ModelAndView("redirect:/products/{product.id}", "product.id", product.getId());
    }

    @GetMapping(value = "{id}/edit")
    public ModelAndView modifyForm(@PathVariable("id") Product product) {
        return new ModelAndView(VIEW_CREATE_PRODUCT, "product", product);
    }

}
