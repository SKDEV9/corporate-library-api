# 📚 Corporate Library API (API de Biblioteca Corporativa)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

🌐 **Status:** Deploy realizado com sucesso! A API está online e pronta para requisições em:
`https://corporate-library-api-production.up.railway.app`

## 💻 Sobre o Projeto
A **Corporate Library API** é um sistema de gerenciamento de biblioteca corporativa desenvolvido em Java com Spring Boot. O objetivo desta API RESTful é gerenciar o acervo de livros da empresa e controlar os empréstimos realizados pelos colaboradores, garantindo a aplicação de regras de negócio estritas e segurança no acesso aos dados.

## ⚙️ Funcionalidades Principais
* **Autenticação e Autorização:** Segurança implementada com Spring Security e Tokens JWT. Divisão de acessos por perfis (`ROLE_ADMIN` e `ROLE_USER`).
* **Gestão de Acervo:** Apenas usuários com perfil de Administrador podem cadastrar novos livros no sistema.
* **Gestão de Empréstimos:** Controle completo de retirada e devolução de livros.
* **Tratamento Global de Exceções:** Respostas de erro padronizadas em formato JSON (ex: 400 Bad Request) para facilitar a integração com o Frontend, mascarando erros internos (500) do servidor.

## 🛡️ Regras de Negócio Implementadas
Durante o desenvolvimento, o foco principal foi garantir a integridade do sistema através de regras estritas:
1. **Limite de Empréstimos:** Um usuário não pode ter mais de 3 livros emprestados simultaneamente.
2. **Disponibilidade:** Um livro não pode ser emprestado se já estiver em posse de outro usuário.
3. **Validação de Dados:** Utilização de DTOs (Data Transfer Objects) e Bean Validation (`@Valid`) para garantir que dados malformados não cheguem à camada de serviço.

## 🛠️ Tecnologias e Ferramentas
* **Java 17+**
* **Spring Boot 3** (Web, Data JPA, Security, Validation)
* **Banco de Dados:** PostgreSQL (Local e Nuvem/Neon)
* **Migrações de Banco de Dados:** Flyway
* **Autenticação:** JSON Web Token (JWT)
* **Deploy:** Railway
* **Testes e Documentação:** Postman

## 🧠 Desafios Superados
Um dos maiores desafios técnicos do projeto foi conciliar as validações de regras de negócio com o **Spring Security**. Inicialmente, os filtros de segurança estavam a interceptar exceções de negócio (como "Limite de livros atingido") e a devolver um `403 Forbidden` genérico ao cliente.
A solução implementada consistiu em ajustar o `SecurityFilterChain` para liberar a rota interna `/error` do Spring, garantindo que o `RestControllerAdvice` pudesse formatar e entregar a mensagem de erro correta ao cliente, mantendo a API 100% segura.

## 🚀 Como Testar e Executar o Projeto

### Opção 1: Testar a API na Nuvem (Recomendado)
A API está online e conectada a um banco de dados de produção. Você pode testar os endpoints diretamente através do Postman:
1. Faça o download do arquivo `postman_collection.json` (adicione este arquivo no repositório!) e importe no seu Postman.
2. Utilize a URL base: `https://corporate-library-api-production.up.railway.app`
3. Crie um usuário, faça o login na rota `/login` para pegar o seu Token JWT, e teste as rotas protegidas!

### Opção 2: Executar Localmente
Para rodar a aplicação na sua própria máquina, siga os passos abaixo:

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SKDEV9/corporate-library-api.git](https://github.com/SKDEV9/corporate-library-api.git)

2. Configure o seu banco de dados PostgreSQL local.

3. (Opcional) Configure as variáveis de ambiente necessárias no application.properties (URL do banco, user, password e JWT Secret).

4. Rode a aplicação via sua IDE de preferência ou utilizando o Maven. A API estará disponível em http://localhost:8080.