# Resolução 04 - CRUD com PostgreSql


## Entrada e saída de dados

- JSON de entrada, CategoriasRequestDTO.

```jason
{
    "nome": "Eletronico"
}
```

- JSON de entrada, ProdutosRequestDTO.

```jason
{  
    "nome": "Computador gamer",
    "descricao": "Computador i7700, placa de video 1050ti",
    "preco": 3500.00
}
```

- JSON de saída, CategoriaResponseDTO.

```jason
{
    "id": 1,
    "nome": "tecnologia",
    "produtos": null
}
```

- JSON de saída, ProdutosResponseDTO.

```jason
{
    "id": 1,
    "nome": "Computador gamer",
    "descricao": "computador i7700, placa de video 1050ti",
    "preco": 3500.0
}
```

## Endpoints da aplicação 

| Método | URL | Descrição
|---|---|---|
| `POST` | localhost:8080/categorias/create | Cria uma categoria recebendo em seu corpo de requisição um body de CategoriasRequestDTO.  Em caso de sucesso, a requisição retorna um status code 200 e um body de CategoriasResponseDTO. O endpoint pode retornar um status code 400 caso o usuário tente registrar uma categoria já cadastrada ou informando valores inválidos no body de requisição.
| `POST` | localhost:8080/produtos/{idCategoria} | Cria um produto, recebendo o id da categoria via URL e um body de ProdutosRequestDTO. Em caso de sucesso, a requisição retorna um status code 200 e um body de ProdutosResponseDTO.  Para registrar um produto novo, é preciso criar sua categoria antes (caso a categoria não exista no banco de dados). O endpoint pode retornar um status code 404 caso o id da categoria seja inválido ou 400 caso o usuário informe valores inválidos no body de requisição.
| `GET` | localhost:8080/produtos/todos | Lista todos os produtos registrados. Em caso de sucesso o enpoint retorna uma lista de ProdutosResponseDTO com todos os produtos (se tiver produtos registrados) e um status code de 200.
| `GET` | localhost:8080/produtos/id/{idProduto} | Realiza a busca de um produto específico através do seu id informado via URL. Em caso de sucesso, a requisição retorna um status code 200 e um body de ProdutosResponseDTO. O endpoint pode retornar um status code 404 caso o id do produto seja inválido.
| `GET` | localhost:8080/categorias | Lista todas as categorias registradas. Em caso de sucesso, o endpoint retorna uma lista de CategoriaResponseDTO com todas as categorias (se tiver categorias registradas) e um status code de 200.
| `PUT` | localhost:8080/produtos/atualizar/{idProduto} | Atualiza um produto, recebendo o id do produto via URL. Em caso de sucesso, retorna um body atualizado de ProdutosResponseDTO e um status code de 200. O endpoint pode retornar um status code 404 caso o id do produto seja inválido.
| `DELETE` | localhost:8080/produtos/deletar/{idProduto} | Deleta um produto, recebendo o id do produto via URL. Em caso de sucesso, não retorna nenhum body com status code 200. O endpoint pode retornar um status code 404 caso o id do produto seja inválido.

## Respostas e Erros
- Criando uma categoria.
  
```jason
{
    "id": 1,
    "nome": "tecnologia",
    "produtos": null
}
```

- Criando um produto.

```jason
{
    "id": 1,
    "nome": "Computador gamer",
    "descricao": "computador i7700, placa de video 1050ti",
    "preco": 3500.0
}
```

- Listando todos os produtos
  
```jason
[
    {
        "id": 1,
        "nome": "Computador gamer",
        "descricao": "computador i7700, placa de video 1050ti",
        "preco": 3500.0
    }
]
```
- Buscando produto via id.
  
```jason
{
    "id": 1,
    "nome": "Computador gamer",
    "descricao": "computador i7700, placa de video 1050ti",
    "preco": 3500.0
}

```
- Atualizando o produto via id.
  
```jason
{
    "id": 1,
    "nome": "Computador gamer completo",
    "descricao": "Computador i7700, placa de video 1050ti c/ mouse gamer zowie",
    "preco": 3500.0
}
```
- listando todas as categorias e seus produtos.
  
```jason
[
    {
        "id": 1,
        "nome": "tecnologia",
        "produtos": [
            {
                "id": 1,
                "nome": "Computador gamer completo",
                "descricao": "Computador i7700, placa de video 1050ti c/ mouse gamer zowie",
                "preco": 3500.0
            }
        ]
    }
]
```

- Erro ao salvar produtos com campos inválidos.

```jason
{
    "timestamp": "2025-07-29T02:39:37.522+00:00",
    "msg": "Erro ao salvar o produto: campos inválidos",
    "detail": "uri=/produtos/1"
}
```
- Erro ao salvar produtos com categoria inválida.
  
```jason
{
    "timestamp": "2025-07-29T02:40:59.295+00:00",
    "msg": "Erro: o id da categoria: 12 não foi encontrado, tente novamente!",
    "detail": "uri=/produtos/12"
}
```
- Erro ao salvar categoria com nome inválido.

```jason
{
    "timestamp": "2025-07-29T02:54:38.057+00:00",
    "msg": "Erro ao salvar a categoria: campos inválidos",
    "detail": "uri=/categorias/create"
}
```
- Erro ao salvar categoria com nome já registrado.

```jason
{
    "timestamp": "2025-07-29T02:54:01.293+00:00",
    "msg": "Erro, a categoria: praia já está registrada!",
    "detail": "uri=/categorias/create"
}
```
- Erro ao procurar produto com id inválido.

```jason
{
    "timestamp": "2025-07-29T02:45:34.548+00:00",
    "msg": "Erro: o id do produto: 123 não foi encontrado, tente novamente!",
    "detail": "uri=/produtos/id/123"
}
```

- Erro ao atualizar produto com id inválido.

```jason
{
    "timestamp": "2025-07-29T02:46:38.253+00:00",
    "msg": "Erro: o id do produto: 13 não foi encontrado, tente novamente!",
    "detail": "uri=/produtos/atualizar/13"
}
```

- Erro ao atualizar o produto com id inválido.

```jason
{
    "timestamp": "2025-07-29T02:47:00.812+00:00",
    "msg": "Erro: o id do produto: 84 não foi encontrado, tente novamente!",
    "detail": "uri=/produtos/deletar/84"
}
```

## Docker

- docker-compose para baixar o projeto do docker hub: https://hub.docker.com/r/math012i/crud-postgressql-app/tags

```yaml
version: '3.8'
services:
  db:
    image: postgres:latest
    container_name: db
    environment:
      - POSTGRES_DB=db_crud
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=1234
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - minha-rede
    healthcheck:
      test: [ "CMD-SHELL", "pg_isready -U postgres" ]
      interval: 5s
      timeout: 5s
      retries: 10

  crud-postgressql:
    image: math012i/crud-postgressql-app:latest
    ports:
      - "8080:8080"
    environment:
      - DB_DATABASE=db_crud
      - DB_USERNAME=postgres
      - DB_PASSWORD=1234
      - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/
      - SPRING_JPA_HIBERNATE_DDL_AUTO=update
    depends_on:
      db:
        condition: service_healthy
    networks:
      - minha-rede
volumes:
  postgres_data:

networks:
  minha-rede:
    external: true

```
