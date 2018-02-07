package nl.avans.ivh11.DemoApplication.repository;

import nl.avans.ivh11.DemoApplication.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
