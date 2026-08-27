package service;

import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class MercadoService {

    private final List<Produto> produtos;
    private final List<Cliente> clientes;
    private final List<Venda> vendas;

    private int proximoCodigoProduto = 1;
    private int proximoCodigoCliente = 1;
    private int proximoCodigoVenda = 1;

    public MercadoService() {
        produtos = new ArrayList<>();
        clientes = new ArrayList<>();
        vendas = new ArrayList<>();
    }

    // PRODUTOS

    public Produto cadastrarProduto(
            String nome,
            String categoria,
            double preco,
            int estoque) {

        Produto produto = new Produto(
                proximoCodigoProduto++,
                nome,
                categoria,
                preco,
                estoque
        );

        produtos.add(produto);

        return produto;
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarProduto(int codigo) {

        for (Produto produto : produtos) {

            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }

        return null;
    }

    public boolean removerProduto(int codigo) {

        Produto produto = buscarProduto(codigo);

        if (produto == null) {
            return false;
        }

        produtos.remove(produto);

        return true;
    }

    public boolean alterarProduto(
            int codigo,
            String nome,
            String categoria,
            double preco,
            int estoque) {

        Produto produto = buscarProduto(codigo);

        if (produto == null) {
            return false;
        }

        produto.setNome(nome);
        produto.setCategoria(categoria);
        produto.setPreco(preco);
        produto.setEstoque(estoque);

        return true;
    }

    // ESTOQUE

    public boolean entradaEstoque(
            int codigo,
            int quantidade) {

        Produto produto = buscarProduto(codigo);

        if (produto == null || quantidade <= 0) {
            return false;
        }

        produto.adicionarEstoque(quantidade);

        return true;
    }

    public boolean saidaEstoque(
            int codigo,
            int quantidade) {

        Produto produto = buscarProduto(codigo);

        if (produto == null || quantidade <= 0) {
            return false;
        }

        return produto.removerEstoque(quantidade);
    }

    // CLIENTES

    public Cliente cadastrarCliente(
            String nome,
            String cpf,
            String telefone) {

        Cliente cliente = new Cliente(
                proximoCodigoCliente++,
                nome,
                cpf,
                telefone
        );

        clientes.add(cliente);

        return cliente;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Cliente buscarCliente(int codigo) {

        for (Cliente cliente : clientes) {

            if (cliente.getCodigo() == codigo) {
                return cliente;
            }
        }

        return null;
    }

    public boolean removerCliente(int codigo) {

        Cliente cliente = buscarCliente(codigo);

        if (cliente == null) {
            return false;
        }

        clientes.remove(cliente);

        return true;
    }

    public boolean alterarCliente(
            int codigo,
            String nome,
            String cpf,
            String telefone) {

        Cliente cliente = buscarCliente(codigo);

        if (cliente == null) {
            return false;
        }

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        return true;
    }

    // VENDA

    public Venda criarVenda(Cliente cliente) {

        return new Venda(
                proximoCodigoVenda++,
                cliente
        );
    }

    public boolean adicionarProdutoVenda(
            Venda venda,
            int codigoProduto,
            int quantidade) {

        Produto produto = buscarProduto(codigoProduto);

        if (produto == null) {
            return false;
        }

        if (quantidade <= 0) {
            return false;
        }

        if (produto.getEstoque() < quantidade) {
            return false;
        }

        // Verifica se o produto já está no carrinho
        for (ItemVenda item : venda.getItens()) {

            if (item.getProduto().getCodigo() == codigoProduto) {

                int novaQuantidade =
                        item.getQuantidade() + quantidade;

                if (produto.getEstoque() < novaQuantidade) {
                    return false;
                }

                item.setQuantidade(novaQuantidade);

                return true;
            }
        }

        ItemVenda item =
                new ItemVenda(produto, quantidade);

        venda.adicionarItem(item);

        return true;
    }

    public boolean removerProdutoVenda(
            Venda venda,
            int codigoProduto) {

        for (ItemVenda item : venda.getItens()) {

            if (item.getProduto().getCodigo() == codigoProduto) {

                venda.getItens().remove(item);

                return true;
            }
        }

        return false;
    }

    public boolean alterarQuantidadeVenda(
            Venda venda,
            int codigoProduto,
            int novaQuantidade) {

        Produto produto = buscarProduto(codigoProduto);

        if (produto == null || novaQuantidade <= 0) {
            return false;
        }

        if (produto.getEstoque() < novaQuantidade) {
            return false;
        }

        for (ItemVenda item : venda.getItens()) {

            if (item.getProduto().getCodigo() == codigoProduto) {

                item.setQuantidade(novaQuantidade);

                return true;
            }
        }

        return false;
    }

    // FINALIZAR VENDA


    public boolean finalizarVenda(
            Venda venda,
            String formaPagamento,
            double valorPago) {

        if (venda == null) {
            return false;
        }

        if (venda.getItens().isEmpty()) {
            return false;
        }

        double total = venda.getTotal();

        // Dinheiro precisa ser suficiente
        if (formaPagamento.equalsIgnoreCase("Dinheiro")) {

            if (valorPago < total) {
                return false;
            }
        }

        // Retira os produtos do estoque

        for (ItemVenda item : venda.getItens()) {

            Produto produto = item.getProduto();

            if (!produto.removerEstoque(
                    item.getQuantidade())) {

                return false;
            }
        }

        venda.finalizarPagamento(
                formaPagamento,
                valorPago
        );

        vendas.add(venda);

        return true;
    }


    // VENDAS


    public List<Venda> listarVendas() {
        return vendas;
    }

    public double calcularFaturamento() {

        double total = 0;

        for (Venda venda : vendas) {
            total += venda.getTotal();
        }

        return total;
    }
}