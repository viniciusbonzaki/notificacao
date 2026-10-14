# Notificações — Agendador de Tarefas

## 📌 Sobre o Projeto

O Agendador de Tarefas tem como objetivo permitir que usuários cadastrem tarefas com datas e horários específicos e recebam notificações por e-mail quando o momento programado se aproximar.

Para organizar as responsabilidades da aplicação, o sistema foi desenvolvido com base na **arquitetura de microserviços**, dividindo suas funcionalidades em serviços independentes e especializados.

O ecossistema é composto por quatro microserviços:

- **API de Usuários:** responsável pelo cadastro, consulta, atualização e exclusão de usuários, incluindo o gerenciamento de seus endereços e telefones.
- **API de Tarefas:** responsável pelo gerenciamento das tarefas cadastradas na aplicação.
- **API de Notificação:** responsável pelo processamento e envio de notificações por e-mail.
- **BFF (Backend for Frontend):** responsável por integrar os demais microserviços e centralizar a comunicação com a camada cliente.

Este repositório é dedicado exclusivamente ao **API de Notificações**, responsável por gerenciar o processo de envio de e-mails relacionados às tarefas cadastradas na aplicação.

Desenvolvida com **Java 17 e Spring Boot**, a API é responsável por realizar o envio de notificações via e-mail para os usuários de suas determinadas tarefas.

Para compor o conteúdo das mensagens, o microsserviço utiliza templates HTML integrados ao **Thymeleaf**, permitindo preencher dinamicamente o modelo do e-mail com as informações da tarefa. O envio é realizado por meio do `JavaMailSender`, configurado no arquivo `application.yaml`, que contém as propriedades necessárias para a comunicação com o servidor SMTP.

## 🎯 Objetivo do Microsserviço

O Microsserviço de Notificações tem como principal objetivo enviar e-mails aos usuários com informações sobre suas tarefas agendadas.

Para isso, a aplicação disponibiliza um endpoint HTTP do tipo `POST`, responsável por receber os dados da tarefa e iniciar o processo de envio da notificação.

O conteúdo do e-mail é gerado dinamicamente a partir de um template HTML, utilizando o Thymeleaf para inserir as informações da tarefa na mensagem.

O envio dos e-mails é realizado por meio do `JavaMailSender`, configurado no arquivo `application.yaml`.

## 🛠️ Tecnologias Utilizadas

- **Java 17:** linguagem de programação utilizada no desenvolvimento.
- **Spring Boot:** framework para construção da aplicação e disponibilização dos endpoints REST.
- **Gradle:** ferramenta de build e gerenciamento de dependências.
- **Spring Web:** suporte à criação de controllers e endpoints HTTP.
- **JavaMailSender:** componente utilizado para o envio de e-mails.
- **Thymeleaf:** mecanismo de templates utilizado para gerar o conteúdo HTML dos e-mails.
- **Docker:** ferramenta utilizada para a containerização da aplicação.
- **YAML:** formato utilizado para as configurações da aplicação.

## 📂 Arquitetura e Organização do Código

O projeto está organizado em camadas, separando as responsabilidades relacionadas às regras de negócio, ao recebimento das requisições HTTP e ao tratamento de exceções.

### Business

Camada responsável pela lógica de negócio relacionada ao envio das notificações.

- **EmailService:** responsável por executar a lógica de envio dos e-mails, utilizando as informações da tarefa e o template HTML.
- **TarefaDTO:** objeto responsável por transportar os dados da tarefa utilizados na composição da notificação.

### Controller

Camada responsável por disponibilizar os endpoints HTTP da aplicação.

- **EmailController:** responsável por receber as requisições `POST` destinadas ao envio de notificações e encaminhar o processamento para a camada de negócio.

### Infrastructure

Camada destinada a componentes de infraestrutura da aplicação.

- **Exceptions:** agrupam as exceções específicas utilizadas pela aplicação. 

### Resources

Diretório destinado aos recursos e arquivos de configuração da aplicação.

- **templates:** contém o arquivo HTML utilizado como modelo para a composição dos e-mails. O Thymeleaf permite preencher o template com as informações da tarefa.
- **application.yaml:** concentra as configurações necessárias para a execução da aplicação e para o envio de e-mails por meio do `JavaMailSender`.

## 🌐 Endpoint da API

O Microsserviço de Notificações disponibiliza um endpoint HTTP do tipo `POST`, responsável por receber os dados necessários para o envio de notificações por e-mail.

O controller responsável por essa operação é o `EmailController`.

| Método HTTP | Recurso | Descrição |
|---|---|---|
| `POST` | Envio de notificações | Recebe as informações de uma tarefa e envia o e-mail correspondente. |

O caminho exato da rota, os campos obrigatórios do corpo da requisição e os códigos de resposta HTTP devem ser documentados de acordo com a implementação do `EmailController`.

## ✉️ Funcionamento do Envio de Notificações

O fluxo de processamento de uma notificação pode ser resumido nas seguintes etapas:

1. O microsserviço recebe uma requisição HTTP do tipo `POST` contendo as informações necessárias para o envio da notificação.
2. O `EmailController` recebe a requisição e encaminha os dados para a camada de negócio.
3. O `EmailService` processa as informações recebidas e prepara o conteúdo do e-mail.
4. O Thymeleaf processa o template HTML, substituindo as expressões do modelo pelos dados correspondentes à tarefa.
5. O `JavaMailSender` realiza o envio do e-mail utilizando as configurações definidas no arquivo `application.yaml`.
6. A aplicação retorna uma resposta HTTP indicando o resultado do processamento, conforme a implementação do endpoint.

Esse fluxo mantém a lógica de envio de e-mails concentrada em um serviço específico, evitando que essa responsabilidade precise ser implementada diretamente nos demais microserviços.

## 🐳 Docker

O projeto utiliza Docker para containerização, facilitando a padronização do ambiente de execução e a implantação da aplicação.

O arquivo `Dockerfile` contém as instruções necessárias para construir a imagem do microsserviço.

## 📚 Conceitos Aplicados

O desenvolvimento deste microserviço envolve conceitos relevantes para a construção de aplicações backend:

- Arquitetura de microserviços.
- Desenvolvimento de APIs REST.
- Separação de responsabilidades em camadas.
- Programação com Java 17 e Spring Boot.
- Implementação de regras de negócio por meio de uma camada de serviço.
- Utilização de DTOs para transferência de dados.
- Construção de templates HTML dinâmicos com Thymeleaf.
- Envio de e-mails com `JavaMailSender`.
- Configuração de propriedades da aplicação com YAML.
- Tratamento de exceções.
- Containerização com Docker.
- Gerenciamento de dependências e build com Gradle.
