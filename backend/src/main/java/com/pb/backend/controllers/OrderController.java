package com.pb.backend.controllers;

import com.pb.backend.dto.OrderDTO;
import com.pb.backend.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Endpoints relacionadas ao gerenciamento de pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(
            summary = "Criar um novo pedido",
            description = "Cria um novo pedido com os dados fornecidos. O momento de criação é automaticamente definido."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json")
            )
    })
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
        dto = orderService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar pedido por ID",
            description = "Retorna os detalhes de um pedido específico pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pedido encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(mediaType = "application/json")
            ),
    })
    public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os pedidos",
            description = "Retorna uma lista com todos os pedidos cadastrados no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de pedidos retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class)
                    )
            )
    })
    public ResponseEntity<Iterable<OrderDTO>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }
}
