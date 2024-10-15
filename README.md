
# libraryManagement

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
MongoDB 5.7.3
Maven 3.3.9
```

## Pré-requisito

## Preparando ambiente
É necessário a  rinciar o banco de dados relacional para o Srping Jpa realizar a interação com as tabelas

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
Na hora de criar o database é necessario passar as informações definidas em seu container  (User,password), segue exemplo:

![Web 1](https://github.com/jhonjiacomasi/libraryManagement/blob/1310/Assets/Config_mysql.png)



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
