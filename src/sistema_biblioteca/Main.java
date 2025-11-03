package sistema_biblioteca;


import java.sql.*;
import java.util.Scanner;

public class Main {
    static Connection conn = Conexao.conectar();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n--- Sistema de Biblioteca ---");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Cadastrar usuário");
            System.out.println("3. Listar livros");
            System.out.println("4. Registrar empréstimo");
            System.out.println("5. Registrar devolução");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarUsuario();
                case 3 -> listarLivros();
                case 4 -> registrarEmprestimo();
                case 5 -> registrarDevolucao();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void cadastrarLivro() {
        try {
            System.out.print("Título: ");
            String titulo = sc.nextLine();
            System.out.print("Autor: ");
            String autor = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO livros (titulo, autor, disponivel) VALUES (?, ?, true)");
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.executeUpdate();

            System.out.println("Livro cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void cadastrarUsuario() {
        try {
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (nome) VALUES (?)");
            ps.setString(1, nome);
            ps.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarLivros() {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM livros");

            while (rs.next()) {
                System.out.printf("ID: %d | %s - %s | Disponível: %s%n",
                    rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"),
                    rs.getBoolean("disponivel") ? "Sim" : "Não");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void registrarEmprestimo() {
        try {
            System.out.print("ID do livro: ");
            int idLivro = sc.nextInt();
            System.out.print("ID do usuário: ");
            int idUsuario = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO emprestimos (id_livro, id_usuario, data_emprestimo) VALUES (?, ?, NOW())");
            ps.setInt(1, idLivro);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();

            PreparedStatement update = conn.prepareStatement(
                "UPDATE livros SET disponivel = false WHERE id = ?");
            update.setInt(1, idLivro);
            update.executeUpdate();

            System.out.println("Empréstimo registrado!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void registrarDevolucao() {
        try {
            System.out.print("ID do empréstimo: ");
            int idEmprestimo = sc.nextInt();

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE emprestimos SET data_devolucao = NOW() WHERE id = ?");
            ps.setInt(1, idEmprestimo);
            ps.executeUpdate();

            PreparedStatement getLivro = conn.prepareStatement(
                "SELECT id_livro FROM emprestimos WHERE id = ?");
            getLivro.setInt(1, idEmprestimo);
            ResultSet rs = getLivro.executeQuery();
            if (rs.next()) {
                int idLivro = rs.getInt("id_livro");
                PreparedStatement update = conn.prepareStatement(
                    "UPDATE livros SET disponivel = true WHERE id = ?");
                update.setInt(1, idLivro);
                update.executeUpdate();
            }

            System.out.println(" Devolução registrada!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
