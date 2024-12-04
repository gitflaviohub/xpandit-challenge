# Movie Service API

Este é um projeto de API REST para gerenciar filmes, desenvolvido em Java usando Spring Boot, H2 como banco de dados em memória, e JUnit para testes. A API permite realizar operações CRUD (Criar, Ler, Atualizar, Deletar) em um recurso de "movie".

## Funcionalidades

- CRUD completo para o recurso "Movie".
- Filtragem de filmes por data de lançamento.
- Inserir vários objetos no mesmo pedido POST.
- Fazer o delete de todos o registos existentes na base de dados.
- Validação de atributos para garantir que o `rank` estejam entre 0 e 10.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database
- JUnit
- Mockito
- Maven
- swagger

## Estrutura do Projeto

movie-api
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── xpandit
│   │   │           └── movie_service
│   │   │               ├── controller
│   │   │               ├── domain
│   │   │                   └── model
│   │   │               ├── repository
│   │   │               └── service
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── xpandit
│                   └── movie_service
│
└── pom.xml



## Instalação

1. Clone o repositório:

   ```bash
   git clone [https://github.com/seu_usuario/movie-api.git](https://github.com/gitflaviohub/xpandit-challenge.git)
   cd movie-service
   mvn clean install
   mvn spring-boot:run

Uso

A API estará disponível em http://localhost:8080/api/movie. Você pode usar ferramentas como Postman ou acessar o http://localhost:8080/swagger-ui/index.html. 
Endpoints

    GET /api/movie - Retorna todos os movie.
    GET /api/movie/{id} - Retorna um movie pelo ID.
    POST /api/movie - Cria um novo movie.
    PUT /api/movie/{id} - Atualiza um movie existente.
    DELETE /api/movie/{id} - Deleta um movie pelo ID.
    GET /api/movie/filer - Retorna filmes filtrados pela data de lançamento, exemplo: http://localhost:8080/api/movie/filter?launchDate=2016-12-13
    POST /api/movie/collection - Cria um ou vários movies, poderá indicar um JSON com um ou vários objetos.
    DEL /api/movie/all - Deleta todos os registos.

Está disponivel o example.postman.movie-service.postman_collection.json que poderá servir de exemplo no Postman para fazer requisições dos endpoints acima citados.


Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir um problema ou enviar um pull request.
