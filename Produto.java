package model;

public class Produto {

    private int codigo;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(int codigo, String nome, String categoria,
                   double preco, int estoque) {

        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void adicionarEstoque(int quantidade) {
        estoque += quantidade;
    }

    public boolean removerEstoque(int quantidade) {

        if (quantidade > estoque) {
            return false;
        }

        estoque -= quantidade;
        return true;
    }

    @Override
    public String toString() {

        return String.format(
                "Código: %d | Produto: %s | Categoria: %s | " +
                        "Preço: R$ %.2f | Estoque: %d",
                codigo,
                nome,
                categoria,
                preco,
                estoque
        );
    }
}