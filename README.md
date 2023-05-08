# Tecnologias

* **Java 11**
* **Spring Boot 2.6.5**
* **API REST**
* **RabbitMQ (Container)**
* **Docker**
* **OATH2**
* **KEYCLOAK**
* **Spring Cloud OpenFeign**
* **Arquitetura de Microsserviços**

# Projeto
* O projeto simula uma analise de crédito, é analisada a renda e a idade do cliente para definir limites.
* É possivel cadastrar 3 bandeiras de cartão no sistema: ELO, MASTERCARD e VISA.
* Não existe limite para renda e nem para o valor do crédito inserido no cadastro do cartão. 


# Comandos Docker

Abaixo serão listados os comandos Docker usados para criar os conteiners, devem ser utilizados conforme sequência abaixo para o correto funcionamento da aplicação:

#### Network da Aplicação 

* `docker network create analise-credito-network`

#### Container KEYCLOAK

* `docker run --name analisecreditokeycloak -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin --network analise-credito-network quay.io/keycloak/keycloak:18.0.0 start-dev`

#### Container Eureka Server

* `docker build --tag analise-credito-eureka .`
* `docker run --name analise-credito-eurekaserver -p 8761:8761 --network analise-credito-network analise-credito-eureka`

#### Container Cartao Microsservice

* `docker build --tag analise-credito-mscartoes .`
* `docker run --name analise-credito-mscartoes --network analise-credito-network -e RABBITMQ_SERVER=analisecreditorabbitmq -e EUREKA_SERVER=analise-credito-eurekaserver -d analise-credito-mscartoes`

#### Container Cliente Microsservice

* `docker build --tag analise-credito-msclientes .`
* `docker run --name analise-credito-msclientes --network analise-credito-network -e EUREKA_SERVER=analise-credito-eurekaserver -d analise-credito-msclientes`

#### Container Avaliador de Crédito Microsservice

* `docker build --tag analise-credito-msavaliadorcredito .`
* `docker run --name analise-credito-msavaliadorcredito --network analise-credito-network -e RABBITMQ_SERVER=analisecreditorabbitmq -e EUREKA_SERVER=analise-credito-eurekaserver -d analise-credito-msavaliadorcredito`

#### Container RABBITMQ

* `docker run --name analisecreditorabbitmq -p 5672:5672 -p 15672:15672 --network analise-credito-network rabbitmq:3.9-management`

#### Container Api Gateway

* `docker build --tag analise-credito-mscloudgateway .`
* `docker run --name mscloudgateway -p 8080:8080 -e EUREKA_SERVER=analise-credito-eurekaserver -e KEYCLOAK_SERVER=analisecreditokeycloak  -e KEYCLOAK_PORT=8080 --network analise-credito-network -d analise-credito-mscloudgateway`


# Collection do Insomnia

* Collection para os principais endpoints via Insomnia. 
* Insomnia_projeto_analise_credito.json na pasta raiz do projeto
### Sequência de requisições sugerida:
* Clientes: Salvar Cliente > Dados Cliente
* Cartoes: Salvar > Get Cartões Renda Até
* Avaliação: Avaliacao Cliente > Solicitacao Emissao Cartao > Situacao Cliente
