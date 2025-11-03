# Sistema de Biblioteca em Java com MySQL

## Descrição

Sistema de gerenciamento de biblioteca desenvolvido em Java, com integração ao MySQL.

O sistema permite:
- Cadastro e consulta de livros;
- Cadastro e consulta de usuários;
- Registro de empréstimos e devoluções de livros;
- Integração com banco de dados MySQL;

---

## Estrutura do Projeto

```
src/
└── sistema-biblioteca/
    ├── Conexao.java
    ├── Livro.java
    ├── Usuario.java
    ├── Emprestimo.java
    └── Main.java
```

---

## Configuração do Banco de Dados

Crie o banco e as tabelas:

```sql
CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    disponivel BOOLEAN
);

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE emprestimos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT,
    id_usuario INT,
    data_emprestimo DATETIME,
    data_devolucao DATETIME,
    FOREIGN KEY (id_livro) REFERENCES livros(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
```

---

## Configuração de Conexão
Coloque a url do banco de dados, usuario e senha:

Exemplo de URL de conexão no Java:

```java
String url = "jdbc:mysql://localhost:3306/biblioteca";
String usuario = "root";
String senha = "SUA_SENHA";
```

---

## Como Rodar

1. Clone o projeto.
2. Configure o banco de dados MySQL.
3. Ajuste usuário e senha no código.
4. Compile e execute o `Main.java`.
