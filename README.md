# Sistema Automotivo — Gestão de Estoque de Veículos

Projeto acadêmico de uma API REST para gerenciamento do estoque de veículos de uma concessionária, desenvolvido com Java 21, Spring Boot, Spring Data JPA e bancos de dados H2 e MySQL.

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de marcas.
- Cadastro, consulta, atualização e exclusão de modelos relacionados às marcas.
- Cadastro, consulta, atualização e exclusão de veículos relacionados aos modelos.
- Filtros de veículos por marca, modelo, preço mínimo e máximo, ano de fabricação e status.
- Validação dos campos e tratamento de erros HTTP.
- Banco H2 em arquivo local para facilitar a execução.
- Perfil alternativo de configuração para MySQL.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- Maven Wrapper
- MySQL 8.0+
- H2 Database
- Jakarta Bean Validation

## Requisitos

- JDK 21.
- MySQL Server 8.0 ou superior, caso queira executar com MySQL.
- MySQL Workbench (opcional).
- Visual Studio Code ou outra IDE Java (opcional).

Não é necessário instalar o Maven separadamente, pois o projeto possui Maven Wrapper.

## Como executar no Windows

1. Extraia a pasta `Sistema_Automotivo`.
2. Abra a pasta `backend` no VS Code.
3. Abra o terminal integrado.
4. Confira se o Java 21 está instalado:

```powershell
java -version
```

5. Execute a aplicação usando o banco H2:

```powershell
.\mvnw.cmd spring-boot:run
```

6. Aguarde a mensagem de inicialização do Spring Boot.

A API ficará disponível em:

`http://localhost:8080`

O H2 utiliza um banco em arquivo local, armazenado em `backend/data/estoque-veiculos`.

## Como executar com MySQL

O projeto também possui configuração para utilizar o MySQL.

1. Instale e inicie o MySQL Server.
2. Se desejar, execute o arquivo `database.sql` no MySQL Workbench para criar o banco `sistema_automotivo`.
3. Abra a pasta `backend` no terminal do PowerShell.
4. Execute o script de inicialização:

```powershell
powershell.exe -NoProfile -ExecutionPolicy Bypass -File ".\iniciar-projeto.ps1"
```

5. Informe a senha do MySQL quando solicitada.
6. Aguarde a inicialização da aplicação.

O script configura o Java 21, solicita a senha do banco sem deixá-la gravada no arquivo e inicia o Spring Boot com o perfil `mysql`.

O Hibernate cria e atualiza as tabelas a partir das entidades do projeto.

**Segurança:** não publique senhas, arquivos de configuração com credenciais ou dados pessoais no GitHub.

## Endpoints principais

| Método | Endpoint | Ação |
|---|---|---|
| GET | `/api/marcas` | Listar marcas |
| POST | `/api/marcas` | Cadastrar marca |
| PUT | `/api/marcas/{id}` | Atualizar marca |
| DELETE | `/api/marcas/{id}` | Excluir marca |
| GET | `/api/modelos` | Listar modelos |
| POST | `/api/modelos` | Cadastrar modelo |
| PUT | `/api/modelos/{id}` | Atualizar modelo |
| DELETE | `/api/modelos/{id}` | Excluir modelo |
| GET | `/api/veiculos` | Listar e filtrar veículos |
| GET | `/api/veiculos/{id}` | Consultar veículo por ID |
| POST | `/api/veiculos` | Cadastrar veículo |
| PUT | `/api/veiculos/{id}` | Atualizar veículo |
| DELETE | `/api/veiculos/{id}` | Excluir veículo |

## Exemplos de filtros

- Por status: `GET /api/veiculos?status=DISPONIVEL`
- Por marca: `GET /api/veiculos?marcaId=1`
- Por modelo: `GET /api/veiculos?modeloId=1`
- Por faixa de preço: `GET /api/veiculos?precoMin=50000&precoMax=100000`
- Por ano e status: `GET /api/veiculos?ano=2022&status=DISPONIVEL`

Status aceitos: `DISPONIVEL`, `RESERVADO`, `VENDIDO` e `DESCONTINUADO`.

## Fluxo de cadastro

Para cadastrar um veículo, siga esta sequência:

1. Cadastre uma marca.
2. Cadastre um modelo informando o ID da marca.
3. Cadastre um veículo informando o ID do modelo.

Exemplo de JSON para cadastrar uma marca:

```json
{
  "nome": "Toyota"
}
```

Exemplo de JSON para cadastrar um modelo:

```json
{
  "nome": "Corolla",
  "marcaId": 1
}
```

Exemplo de JSON para cadastrar um veículo:

```json
{
  "modeloId": 1,
  "cor": "Prata",
  "anoFabricacao": 2022,
  "preco": 112900.00,
  "quilometragem": 35000,
  "status": "DISPONIVEL"
}
```

## Arquitetura e orientação a objetos

O projeto utiliza uma organização em camadas:

- **Entidades:** `Marca`, `Modelo` e `Veiculo`.
- **Enum:** `StatusVeiculo`, que representa as situações dos veículos no estoque.
- **Repositórios:** interfaces Spring Data JPA para acesso e persistência dos dados.
- **Controllers:** responsáveis por disponibilizar os endpoints REST.
- **Encapsulamento:** atributos privados e métodos de acesso.
- **Associações:** um modelo pertence a uma marca e um veículo referencia um modelo.
- **Validação:** Jakarta Bean Validation.
- **Tratamento de erros:** `ApiExceptionHandler` centraliza o tratamento das exceções da API.

As interfaces de repositório utilizam recursos de herança oferecidos pelo Spring Data JPA, como `JpaRepository` e `JpaSpecificationExecutor`. No domínio, as relações entre marca, modelo e veículo são representadas por associações.

## Documentação e testes

A pasta `docs` contém o relatório teórico, o plano de testes e o roteiro para apresentação em vídeo.

Também estão disponíveis arquivos de requisições HTTP para auxiliar na execução dos testes da API.

Os testes realizados durante o desenvolvimento incluíram operações de cadastro, consulta, atualização, exclusão e aplicação de filtros de veículos.