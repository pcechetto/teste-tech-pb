package com.pb.backend.services;

import com.pb.backend.dto.OrderDTO;
import com.pb.backend.entities.Order;
import com.pb.backend.repositories.OrderRepository;
import com.pb.backend.services.exceptions.ResourceNotFoundException;
import com.pb.backend.tests.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void save_ShouldReturnOrderDTO_WhenValidOrderDTO() {
        OrderDTO orderDTO = Factory.createOrderDTO();
        Order order = Factory.createOrder();

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDTO result = orderService.save(orderDTO);

        assertNotNull(result);
        assertEquals("abc", result.getId());
        assertNotNull(result.getMoment());
        assertEquals("PENDING", result.getStatus());
        assertEquals("Dummy Doe", result.getCustomer());
        assertEquals("Laptop", result.getProduct());
        assertEquals(2, result.getQuantity());
        assertEquals(1500.0, result.getPrice());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void findById_ShouldReturnOrderDTO_WhenOrderExists() {
        Order order = Factory.createOrder();
        when(orderRepository.findById("abc")).thenReturn(Optional.of(order));

        OrderDTO result = orderService.findById("abc");

        assertNotNull(result);
        assertEquals("abc", result.getId());
        assertNotNull(result.getMoment());
        assertEquals("PENDING", result.getStatus());
        assertEquals("Dummy Doe", result.getCustomer());
        assertEquals("Laptop", result.getProduct());
        assertEquals(2, result.getQuantity());
        assertEquals(1500.0, result.getPrice());

        verify(orderRepository, times(1)).findById("abc");
    }

    @Test
    void findById_ShouldThrowResourceNotFoundException_WhenOrderNotFound() {
        when(orderRepository.findById("nonexistent")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.findById("nonexistent"));

        verify(orderRepository, times(1)).findById("nonexistent");
    }

    @Test
    void findAll_ShouldReturnListOfOrderDTOs() {
        Order order1 = Factory.createOrder();
        Order order2 = Factory.createCustomOrder("COMPLETED", "Jane Doe", "Keyboard", 1, 100.0);

        List<Order> orders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(orders);

        Iterable<OrderDTO> result = orderService.findAll();

        assertNotNull(result);
        List<OrderDTO> resultList = (List<OrderDTO>) result;
        assertEquals(2, resultList.size());

        OrderDTO firstOrder = resultList.get(0);
        assertEquals("abc", firstOrder.getId());
        assertEquals("PENDING", firstOrder.getStatus());
        assertEquals("Dummy Doe", firstOrder.getCustomer());

        OrderDTO secondOrder = resultList.get(1);
        assertEquals("123", secondOrder.getId());
        assertEquals("COMPLETED", secondOrder.getStatus());
        assertEquals("Jane Doe", secondOrder.getCustomer());

    }
}
