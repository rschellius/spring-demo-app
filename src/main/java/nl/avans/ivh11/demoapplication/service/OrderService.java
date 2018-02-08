package nl.avans.ivh11.demoapplication.service;

import nl.avans.ivh11.demoapplication.domain.Order;
import nl.avans.ivh11.demoapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository repo) {
        this.orderRepository = repo;
    }

    public ArrayList<Order> getProducts() {
        return (ArrayList<Order>) this.orderRepository.findAll();
    }

    public Order createProduct(Order order) {
        return this.orderRepository.save(order);
    }
}
