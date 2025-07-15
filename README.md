# Teste Tech PB

Este projeto é composto por um backend (Spring Boot + MongoDB) e um frontend (React + Vite) para gerenciamento de pedidos.

## Como rodar o projeto

1. **Pré-requisitos:**

   - Docker e Docker Compose instalados.

2. **Clone o repositório:**

   ```sh
   git clone https://github.com/pcechetto/teste-tech-pb
   cd teste-tech-pb
   ```

3. **Suba todos os serviços:**
   ```sh
   docker compose up -d
   ```

Isso irá iniciar:

- MongoDB (porta 27017)
- Backend Spring Boot (porta 8080)
- Frontend React (porta 3000)

## Acessando o projeto

- **Frontend:**
  - Acesse [http://localhost:3000](http://localhost:3000) para utilizar a interface de gerenciamento de pedidos.
- **Backend (API):**
  - Acesse [http://localhost:8080](http://localhost:8080) para endpoints da API REST.
- **Documentação Swagger:**
  - Acesse [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).
- **MongoDB:**
  - Disponível em `localhost:27017` (útil para ferramentas como MongoDB Compass).

## Recursos e funcionalidades

- Cadastro e listagem de pedidos
- Integração total entre frontend e backend via API REST
- Persistência dos dados em MongoDB
- Interface web
- Testes com JUnit no backend

## Decisões técnicas

- **Docker Compose**: Facilita o setup do ambiente, permitindo subir todos os serviços com um único comando.
- **Spring Boot + MongoDB**: Backend robusto, com fácil integração ao banco NoSQL.
- **React + Vite**: Frontend moderno, rápido e com hot reload para desenvolvimento.
- **Multi-stage build no Docker**: Otimiza o tamanho das imagens e separa build de execução.
- **Rede bridge customizada**: Garante comunicação isolada e eficiente entre os containers.
