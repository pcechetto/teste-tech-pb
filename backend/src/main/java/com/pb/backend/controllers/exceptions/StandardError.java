package com.pb.backend.controllers.exceptions;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@SuppressWarnings("unused")
@Schema(description = "Estrutura de resposta para erros")
public class StandardError {
    @Schema(description = "Timestamp do erro", example = "2024-01-15T10:30:00Z")
    private Instant timestamp;
    @Schema(description = "Código de status HTTP", example = "404")
    private Integer status;
    private String error;
    @Schema(description = "Mensagem de erro", example = "Pedido não encontrado")
    private String message;
    @Schema(description = "Caminho da requisição", example = "/api/v1/pedidos/123")
    private String path;

    public StandardError() {
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
