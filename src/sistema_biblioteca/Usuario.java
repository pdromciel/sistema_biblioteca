package sistema_biblioteca;

public class Usuario {
    private int id;
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
}
