
# libraryManagement

[![NPM]](https://github.com/jhonjiacomasi/libraryManagement/blob/main/LICENSE)

# Sobre o projeto

LibraryManagement é uma aplicação de gestão de biblioteca que permita o cadastro de livros, usuários, empréstimos,devoluções e forneça recomendações de livros, e também é possivel realizar a pesquisa através da plataforma Google Books.

## Layout web

![Web 1](https://github.com/jhonjiacomasi/libraryManagement/blob/1310/Assets/User.png)

![Web 1](https://github.com/jhonjiacomasi/libraryManagement/blob/1310/Assets/Home.png)

## Modelo conceitual
![Modelo Conceitual](https://github.com/jhonjiacomasi/libraryManagement/blob/1310/Assets/Modelo%20conceitual.png)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)


# Tecnologias utilizadas 
## Back end
- Java 17
- Spring Boot 3x 
- JPA / Hibernate
- Webflux
- Validation
- Security
- lombok
- Maven
## Front end
- HTML / CSS / JS 
- ReactJS
## Banco de dados
- Banco de dados : Datagrid Jetbrains (Mysql)
- Container docker (Mysql server 5.7.3)
- Banco de dados opcional: Mysql Workbench , Postgresql

# clonar repositório
git clone https://github.com/jhonjiacomasi/libraryManagement

# entrar na pasta do projeto back end
cd backend

# Setup da aplicação (local)

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 17
Mysql 5.7.3
Maven 3.3.9
```

## Pré-requisito

## Preparando ambiente
É necessário a  iniciar o banco de dados relacional para o Srping Jpa realizar a interação com as tabelas

# executar o projeto
```
./mvnw spring-boot:run
```

É preciso compilar o código e baixar as dependências do projeto:

```
mvn clean package
```

Finalizado esse passo, vamos iniciar a aplicação:

```
mvn spring-boot:run
```


## Preparando ambiente para rodar a imagem do mysql via Docker

Criar e executar container do  mysql server

```
version: '3'
services:
  db:
    container_name: mysql-db
    image: mysql:5.7
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: "200713" (Opcional)
      MYSQL_DATABASE: "librarymanagement" ("SUA BASE DE DADOS")
      MYSQL_USER: ("Seu Usuario)
      MYSQL_PASSWORD: ("Sua Senha")
    ports:
      - "3306:3306" 
    volumes:
      - "./mysql-data:/var/lib/mysql"
  ```
      
## Execuntando o Docker
Baixar as dependência e criar imagem da aplicação exexutando o seguinte comando:

```
docker-compose up -d
```

Após a execucão a imagem rodara localmente e poderá realizar a execução do seu servidor sql em sua IDE

## criando o database explorer em sua aplicação:
Na hora de criar o database é necessario passar as informações definidas em seu container  (User,password) segue exemplo:

![Web 1](https://github.com/jhonjiacomasi/libraryManagement/blob/1310/Assets/Config_mysql.png)

# APIs

O projeto disponibiliza uma API em 3 contextos diferentes: Users, Books,loans  onde utilizam o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON.

Segue abaixo as APIs disponíveis no projeto:

#### Users

 - /v1/saveUser    (POST)
 - /v1/updateUser/ (PUT)
 - /v1/allUsers    (GET)
 - /v1/UserById    (GET)
 - /v1/deleteUser  (DELETE)
   
 - Espera atributos para serem critérios de inserção no body da requisição, exemplo:
    ```
   {
        "name": "João antonio",
        "email": "Joaoantonio@gmail.com",
        "telephone": "11963395478"
    }
    ```
    
   - resposta da requisição para inserção da base de dados, exemplo:

   ```
    {
    "id": 3,
     "name": "João antonio",
     "email": "Joaoantonio@gmail.com",
     "registrationdate": "14-10-2024",
     "telephone": "11963395478"
     }
   ```

#### BOOKS

 - /v1/savebook    (POST)
 - /v1/updateBook  (PUT)
 - /v1/allBooks    (GET)
 - /v1/bookById    (GET)
 - /v1/googleBooks (GET)  Realiza a busca de livros apartir da api do googleBooks
 - /v1/deletebook  (DELETE)
 
   
 - Espera atributos para serem critérios de inserção no body da requisição, exemplo:
    ```
   {
    "title": "Teste unitario",
    "author": "Kevlin teste",
    "isbn": "te4563",
    "publishDate": "2024-02-28",
    "category": "Tecnologia"
   }

    ```
    
   - resposta da requisição para inserção da base de dados, exemplo:

   ```
    {
    "id": "73a89929-0ac3-41a9-8c7d-5d9a5b9f884c",
    "title": "Teste unitario",
    "author": "Kevlin teste",
    "isbn": "te4563",
    "publishDate": "2024-02-28T00:00:00.000+00:00",
    "category": "Tecnologia"
    }
   ```

   #### Loans

 - /v1/saveloans    (POST)
 - /v1/updateloans/ (PUT)
 
   
 - Espera atributos para serem critérios de inserção no body da requisição,com dados do Id Usuario e Id do livro exemplo:
    ```
   {
    "user_id":"6",
    "books_id":"e7f86c40-91ca-4b23-9371-f58797061cb9",
    "status":true
   }
    ```
    
   - resposta da requisição para  guardar somente alguns parametros na base de dados inserção da base de dados, exemplo:

   ```
    {
    "id": "4590f4f8-c22f-44dc-93f5-d0711547e347",
    "user": {
        "id": 1,
        "name": "Pedro ",
        "email": "pedrolo@gmail.com",
        "registrationdate": "2024-10-14 10:21:48.162398",
        "telephone": "49999123456706"
    },
    "books": {
        "id": "40cd9e7e-0efa-4c53-a7b2-aa0cfc84ab81",
        "title": "Arquitetura Limpa: o Guia do Artesão Para Estrutura e Design de Software",
        "author": "Kevlin Henney",
        "isbn": "B085PP6Y8P",
        "publishDate": "2024-01-24T00:00:00.000+00:00",
        "category": "Tecnologia"
    },
    "loansDate": "14-10-2024",
    "returnDate": "30-10-2024",
    "status": false
    }
   ```

## Front end web
Pré-requisitos: npm / yarn

```bash
# clonar repositório
git clone https://github.com/jhonjiacomasi/libraryManagement

# entrar na pasta do projeto front end web
cd front-web

# instalar dependências
npm install

# executar o projeto
npm run Dev
```

# Autor

Jhonatan Santana Giacomasi Barros

https://www.linkedin.com/in/jhonatan-giacomasi/
