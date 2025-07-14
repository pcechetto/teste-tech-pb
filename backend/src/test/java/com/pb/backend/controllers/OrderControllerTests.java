package com.pb.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pb.backend.dto.OrderDTO;
import com.pb.backend.services.OrderService;
import com.pb.backend.services.exceptions.ResourceNotFoundException;
import com.pb.backend.tests.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OrderController.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderService orderService;

    @Test
    void createOrder_ShouldReturnCreatedOrder_WhenValidInput() throws Exception {
        OrderDTO orderDTO = Factory.createOrderDTO();
        when(orderService.save(any(OrderDTO.class))).thenReturn(orderDTO);

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/v1/pedidos/abc"))
                .andExpect(jsonPath("$.id").value("abc"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.customer").value("Dummy Doe"))
                .andExpect(jsonPath("$.product").value("Laptop"))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.price").value(1500.0));
    }

    @Test
    void findById_ShouldReturnOrder_WhenOrderExists() throws Exception {
        // Arrange
        OrderDTO orderDTO = Factory.createOrderDTO();
        when(orderService.findById("abc")).thenReturn(orderDTO);

        // Act & Assert
        mockMvc.perform(get("/api/v1/pedidos/abc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("abc"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.customer").value("Dummy Doe"))
                .andExpect(jsonPath("$.product").value("Laptop"))
                .andExpect(jsonPath("$.quantity").value(2))
                .andExpect(jsonPath("$.price").value(1500.0));
    }

    @Test
    void findById_ShouldReturn404_WhenOrderNotFound() throws Exception {
        when(orderService.findById("nonexistent")).thenThrow(new ResourceNotFoundException("Order not found"));

        mockMvc.perform(get("/api/v1/pedidos/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ShouldReturnListOfOrders() throws Exception {
        OrderDTO order1 = Factory.createOrderDTO();
        OrderDTO order2 = Factory.createCustomOrderDTO("COMPLETED", "Alice Smith", "Monitor", 1, 250.0);
        OrderDTO order3 = Factory.createCustomOrderDTO("SHIPPED", "Bob Johnson", "Webcam", 2, 40.0);

        List<OrderDTO> orders = Arrays.asList(order1, order2, order3);
        when(orderService.findAll()).thenReturn(orders);

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value("abc"))
                .andExpect(jsonPath("$[0].customer").value("Dummy Doe"))
                .andExpect(jsonPath("$[0].product").value("Laptop"))
                .andExpect(jsonPath("$[1].id").value("123"))
                .andExpect(jsonPath("$[1].customer").value("Alice Smith"))
                .andExpect(jsonPath("$[1].product").value("Monitor"))
                .andExpect(jsonPath("$[2].id").value("123"))
                .andExpect(jsonPath("$[2].customer").value("Bob Johnson"))
                .andExpect(jsonPath("$[2].product").value("Webcam"));
    }
}
