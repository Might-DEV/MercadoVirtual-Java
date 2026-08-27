package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int codigo;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private String formaPagamento;
    private double valorPago;
    private double troco;
    private LocalDateTime data;

    public Venda(int codigo, Cliente cliente) {

        this.codigo = codigo;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.data = LocalDateTime.now();
    }

    public void adicionarItem(ItemVenda item) {
        itens.add(item);
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public double getTroco() {
        return troco;
    }

    public void finalizarPagamento(
            String formaPagamento,
            double valorPago) {

        this.formaPagamento = formaPagamento;
        this.valorPago = valorPago;

        if (formaPagamento.equalsIgnoreCase("Dinheiro")) {
            this.troco = valorPago - getTotal();
        } else {
            this.troco = 0;
        }
    }

    public double getTotal() {

        double total = 0;

        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }

        return total;
    }

    public String getDataFormatada() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return data.format(formato);
    }

    @Override
    public String toString() {

        return String.format(
                "Venda #%d | Cliente: %s | Data: %s | Total: R$ %.2f",
                codigo,
                cliente != null
                        ? cliente.getNome()
                        : "Consumidor",
                getDataFormatada(),
                getTotal()
        );
    }

    public int getData() {
        return 0;
    }
}