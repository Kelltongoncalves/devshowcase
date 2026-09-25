# DevShowcase API

Backend em Java/Spring Boot para cadastro de perfis, projetos e tecnologias.

## Requisitos
- Java 17
- Maven
- MySQL

## Banco
O projeto usa o banco `devshowcase`. Ajuste usuário e senha em `src/main/resources/application.properties`.

## Endpoints
- POST /api/profiles
- GET /api/profiles/{id}
- POST /api/technologies
- GET /api/technologies
- POST /api/projects
- GET /api/projects

## Exemplo de Profile
POST http://localhost:8080/api/profiles

{
  "name": "Kelton Holanda",
  "email": "kelton@email.com",
  "bio": "Desenvolvedor Java e Spring Boot"
}

## Exemplo de Technology
POST http://localhost:8080/api/technologies

{
  "name": "Spring Boot"
}

## Exemplo de Project
POST http://localhost:8080/api/projects

{
  "title": "DevShowcase API",
  "description": "API para apresentação de projetos",
  "url": "https://github.com/usuario/devshowcase",
  "profileId": 1,
  "technologyIds": [1, 2, 3]
}
