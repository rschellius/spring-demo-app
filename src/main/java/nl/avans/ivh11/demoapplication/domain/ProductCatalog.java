package nl.avans.ivh11.demoapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ProductCatalog {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = javax.persistence.CascadeType.ALL)
	private Map<Long, Product> products = new HashMap<>();
	
	// add new product to catalog and indicate number of stock items
	// precondition: product must have an id!
	public void add(Product product, int quantity) {
		assert(product.getId() != null);
		
		products.put(product.getId(), product);
	}
	
	// precondition: product in catalog
	// precondition: at least one product in stock
	public Product decrementStock(Long productId) {
		assert(products.containsKey(productId));
//		assert(products.get(productId).getQuantity() >= 0);
		
//		StockItem si = products.get(productId);
//		products.put(productId, si.decrementStock());
		return null; //si.getProduct();
	}

	public ProductCatalog() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Long, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<Long, Product> products) {
		this.products = products;
	}
}
