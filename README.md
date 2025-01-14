# Tenant Rental Management

Este é um projeto de gerenciamento de aluguel de inquilinos desenvolvido com Java e Spring Boot. Ele permite gerenciar contratos de aluguel, inquilinos e propriedades.

## Funcionalidades

- **Gerenciamento de Inquilinos**: CRUD para gerenciar informações dos inquilinos.
- **Gerenciamento de Propriedades**: CRUD para gerenciar informações das propriedades imobiliárias.
- **Gerenciamento de Contratos**: CRUD para gerenciar contratos de aluguel entre inquilinos e propriedades.
- **Links HATEOAS**: Facilita a navegação entre os recursos.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (ou outra base de dados, conforme configuração)
- Maven
- HATEOAS para links de navegação

## Pré-requisitos

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
- [Maven](https://maven.apache.org/) para gerenciamento de dependências
- Um IDE ou editor de texto com suporte a Java (como IntelliJ IDEA, Eclipse, ou VSCode)

## Configuração do Projeto

1. **Clone o repositório**

   ```bash
   git clone https://github.com/seu-usuario/TenantRentalManagement.git

2. **Navegue até o diretório do projeto**

    cd TenantRentalManagement

3. **Instale as dependências do Maven**

    mvn clean install

4. **Configure o banco de dados**

    Por padrão, o projeto está configurado para usar o banco de dados H2 em memória. Você pode alterar as configurações no arquivo application.properties para usar outro banco de dados.

5. **Execute a aplicação**

    mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.

## Endpoints da API
## Person Endpoints
- **POST /persons**: Cria um novo inquilino.
- **GET /persons**: Retorna todos os inquilinos.
- **GET /persons/{id}**: Retorna um inquilino específico pelo ID.
- **PUT /persons/{id}**: Atualiza um inquilino pelo ID.
- **DELETE /persons/{id}**: Exclui um inquilino pelo ID.
## Real Estate Endpoints
- **POST /realestates**: Cria uma nova propriedade.
- **GET /realestates**: Retorna todas as propriedades.
- **GET /realestates/{id}**: Retorna uma propriedade específica pelo ID.
- **PUT /realestates/{id}**: Atualiza uma propriedade pelo ID.
- **DELETE /realestates/{id}**: Exclui uma propriedade pelo ID.
## Contract Endpoints
- **POST /contracts**: Cria um novo contrato de aluguel.
- **GET /contracts**: Retorna todos os contratos.
- **GET /contracts/{id}**: Retorna um contrato específico pelo ID.
- **PUT /contracts/{id}**: Atualiza um contrato pelo ID.
- **DELETE /contracts/{id}**: Exclui um contrato pelo ID.
## Modelo de Dados
## PersonModel
- **UUID idPerson**
- **String name**
- **String email**
- **String phoneNumber**
## RealEstateModel
- **UUID idRealEstate**
- **String address**
- **String type**
- **double price**
## ContractModel
- **UUID idContract**
- **PersonModel person**
- **RealEstateModel realEstate**
- **Date startDate**
- **Date endDate**
- **double rentAmount**
## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença
Este projeto é licenciado sob os termos da licença MIT. Veja o arquivo LICENSE para mais detalhes.


Este `README.md` fornece uma descrição clara do projeto, incluindo funcionalidades, configuração, endpoints da API, e outros detalhes essenciais para ajudar desenvolvedores a entender e contribuir com o projeto.
