package nl.avans.ivh11.demoapplication.repository;

import nl.avans.ivh11.demoapplication.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
