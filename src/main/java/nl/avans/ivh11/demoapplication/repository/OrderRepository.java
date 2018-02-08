package nl.avans.ivh11.demoapplication.repository;

import nl.avans.ivh11.demoapplication.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
