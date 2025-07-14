package com.pb.backend.services;

import com.pb.backend.dto.OrderDTO;
import com.pb.backend.entities.Order;
import com.pb.backend.repositories.OrderRepository;
import com.pb.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = new Order();

        order.setId(orderDTO.getId());
        order.setMoment(Instant.now());
        order.setStatus(orderDTO.getStatus());
        order.setCustomer(orderDTO.getCustomer());
        order.setProduct(orderDTO.getProduct());
        order.setQuantity(orderDTO.getQuantity());
        order.setPrice(orderDTO.getPrice());
        orderDTO.getTotal();

        return new OrderDTO(orderRepository.save(order));
    }

    public OrderDTO findById(String id) {
        return new OrderDTO(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado")));
    }

    public Iterable<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderDTO::new).toList();
    }
}
