package com.example.orderservice.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderApplicationService {

    private static final Logger log = LoggerFactory.getLogger(OrderApplicationService.class);

    private final OrderRepository orderRepository;
    private final ProductService productService;

    OrderApplicationService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    List<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    Order createOrder(CreateOrderData createOrderData) {
        final Order order = Order.create(createOrderData.getProductId(), createOrderData.getShippingAddress());

        final List<Product> products = productService.fetchProducts();
        order.validate(products);
        orderRepository.save(order);

        log.info("Created order: " + order);

        return order;
    }
}
