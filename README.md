
# Itaú Unibanco - Desafio de Programação

Este repositório contém a resolução feita por mim do desafio proposto pelo Itaú para desenvolvedor junior.
Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos.

[Repositório do Desafio](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)

---

## Tecnologias

- Java 17
- Spring Boot 3.5.4
- Swagger
- Lombok
- Maven
- Docker

---

## Endpoints

Base URL: `http://localhost:8080`

| Método | Endpoint       | Descrição             | Corpo (JSON)   | Parâmetros                                     |
|--------|----------------|-----------------------|----------------|------------------------------------------------|
| POST   | `/transacao`   | Receber Transações    | `TransacaoDto` | -                                              |
| DELETE | `/transacao`   | Limpar Transações     | -              | -                                              |
| GET    | `/estatistica` | Calcular Estatisticas | -              | `segundos` (**Não Obrigatório**, Default: 60s) |

#### Exemplos de DTOs

`TransacaoDto`
```json
{
  "valor": 123.45,
  "dataHora": "2025-08-15T09:31:54.072-03:00"
}
```
`EstatisticaDto`
```json
{
  "count": 10,
  "sum": 1234.56,
  "avg": 123.456,
  "min": 12.34,
  "max": 123.56
}
```

---

## Swagger e Actuator

Para acessar a documentação e as métricas da API:

- **Swagger**: `http://localhost:8080/swagger-ui/index.html`
- **Actuator**: `http://localhost:8081/actuator/` - `health`, `info`, `metrics`

## Testando a Aplicação

1. Clone o repositório
2. Compile o projeto:
```
 mvn clean install
```
3. Execute o projeto:
```
 mvn spring-boot:run
```
4. Rodar em um container Docker (Opcional):
```
 docker build -t transacoes-api .
```
5. Execute o Container:
```
 docker run -p 8080:8080 -p 8081:8081 transacoes-api
```
---

**Desenvolvido por Tiago Azevedo de Souza**
