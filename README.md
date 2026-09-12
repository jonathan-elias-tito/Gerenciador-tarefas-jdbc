# Gerenciador de Tarefas (Task Manager) - (Java & JDBC + DAO Pattern)

Sistema de gerenciamento de tarefas desenvolvido em Java utilizando **JDBC** para persistência de dados em banco relacional e a arquitetura **DAO (Data Access Object)** para isolamento de regras de persistência.

---

## 🚀 Funcionalidades

### 👤 Gerenciamento de Usuários (`UserDao`)
* **Inserção (`insert`)**: Cadastro de novos usuários.
* **Atualização (`update`)**: Edição dos dados de um usuário existente.
* **Remoção (`deleteById`)**: Exclusão de usuário por ID.
* **Consulta por ID (`findById`)**: Busca detalhada de um usuário específico.
* **Listagem Geral (`findAll`)**: Busca de todos os usuários cadastrados.

### 📋 Gerenciamento de Tarefas (`TaskDao`)
* **Inserção (`insert`)**: Criação de novas tarefas vinculadas a um usuário.
* **Atualização (`update`)**: Atualização de dados da tarefa (título, descrição, data de entrega, status e vínculo de usuário).
* **Remoção (`deleteById`)**: Exclusão de tarefa por ID.
* **Consulta por ID (`findById`)**: Busca de tarefa com dados do usuário associado via `INNER JOIN`.
* **Listagem Geral (`findAll`)**: Retorna todas as tarefas com tratamento para evitar duplicação de objetos `User` em memória.
* **Filtro por Usuário (`findByUser`)**: Busca de tarefas atribuídas a um usuário específico.
* **Filtro por Status (`findByStatus`)**: Consulta de tarefas por estado (ex: "Pendente", "Concluido").

---

## 🛠️ Tecnologias e Padrões Utilizados

* **Linguagem**: Java 25 (JavaSE)
* **Persistência**: JDBC (`PreparedStatement`, `ResultSet`, `Statement.RETURN_GENERATED_KEYS`)
* **Padrões de Projeto**: 
  * **DAO Pattern**: Separação das regras de acesso a dados em interfaces (`UserDao`, `TaskDao`) e implementações JDBC.
  * **Factory Pattern**: Centralização da instanciação das implementações via `DaoFactory`.
* **Tratamento de Exceções**: Camada personalizada com `DbException`.
* **Gerenciamento de Recursos**: Utilização de `try-with-resources` e métodos utilitários em `DbConn` para fechamento seguro de conexões, *statements* e *result sets*.

---

## 📁 Estrutura do Projeto

```text
src/
 ├── application/
 │    ├── Program.java        # Testes das operações de Task
 │    └── Program2.java       # Testes das operações de User
 ├── db/
 │    ├── DbConn.java         # Conexão e fechamento de recursos JDBC
 │    └── DbException.java    # Exceção customizada de banco de dados
 ├── model.dao/
 │    ├── DaoFactory.java     # Fábrica de instâncias DAO
 │    ├── TaskDao.java        # Interface de operações para Task
 │    └── UserDao.java        # Interface de operações para User
 ├── model.dao.impl/
 │    ├── TaskJDBC.java       # Implementação JDBC para Task
 │    └── UserJDBC.java       # Implementação JDBC para User
 └── model.entities/
      ├── Task.java           # Entidade Task
      └── User.java           # Entidade User
