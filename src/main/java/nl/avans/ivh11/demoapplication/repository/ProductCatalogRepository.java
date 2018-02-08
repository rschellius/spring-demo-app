package nl.avans.ivh11.demoapplication.repository;


import nl.avans.ivh11.demoapplication.domain.ProductCatalog;
import org.springframework.data.repository.CrudRepository;

public interface ProductCatalogRepository extends CrudRepository<ProductCatalog, Long> {

}
