package sistema_biblioteca;

import java.sql.Date;

public class Emprestimo {
    private int id;
    private int idLivro;
    private int idUsuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int idLivro, int idUsuario, Date dataEmprestimo) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.dataEmprestimo = dataEmprestimo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdLivro() { return idLivro; }
    public int getIdUsuario() { return idUsuario; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Date dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}
