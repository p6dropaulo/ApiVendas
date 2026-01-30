# 🛒 ApiVendas - Sistema de Gestão de Vendas

Este repositório contém a **API de Vendas**, que atua como o orquestrador central de um ecossistema de microserviços desenvolvido para a disciplina de Infraestrutura em Nuvem e Sistemas Distribuídos.

## 💻 Sobre o Projeto

O projeto foi desenvolvido como exercício da disciplina de **Infraestrutura em Nuvem e Sistemas Distribuídos**. O objetivo principal foi aplicar conceitos de arquitetura REST, comunicação entre microserviços e deploy em nuvem.

## 🔗 O projeto é composto por três partes que trabalham em conjunto:

- ApiCartao: Microserviço responsável por gerenciar a base de dados de cartões no Supabase.
- ApiVendas (Este repositório): Processa as vendas e valida as transações consultando a ApiCartao.
- FrontVendas: Interface web para interação do usuário final.

### Fluxo da Operação:

1. Recebe uma requisição de venda.
2. Utiliza um **REST Client** para perguntar à `ApiCartao` se o número do cartão existe.
3. Se validado, a venda é persistida no banco de dados local.
4. Retorna o feedback para o front-end.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Quarkus Framework** 
* **Rest Client** 
* **Documentação (Swagger)**
* **Docker & Render** 

## 🔌 Endpoints Principais

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/vendas` | Registra uma nova venda (valida o cartão antes) |
| `GET` | `/vendas` | Lista todas as vendas realizadas |

## ⚙️ Como Executar

### Rodando Localmente

1. Clone o repositório.
2. Configure a URL da API de Cartões no arquivo `src/main/resources/application.properties`:
   ```properties
   cartao-api/mp-rest/url=https://sua-api-cartao-url.com
   ```

3. Execute o comando:
   ```bash
   ./mvnw quarkus:dev
   ```

4. Acesse a documentação Swagger em: `http://localhost:8080/q/swagger-ui/`

### Rodando via Docker

```bash
docker build -t api-vendas .
docker run -p 8080:8080 api-vendas
```

## 🌐 Deploy

A API está configurada para deploy automático no Render utilizando o `Dockerfile` 
