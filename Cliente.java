package model;

public class Cliente {

    private int codigo;
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(int codigo, String nome, String cpf, String telefone) {

        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {

        return String.format(
                "Código: %d | Nome: %s | CPF: %s | Telefone: %s",
                codigo,
                nome,
                cpf,
                telefone
        );
    }
}