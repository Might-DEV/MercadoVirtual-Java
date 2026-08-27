import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import service.MercadoService;

import java.util.Scanner;

//---------- PROGRAMA PARA TESTAR PELO TERMINAL --------------//
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static MercadoService mercado = new MercadoService();

    public static void main(String[] args) {

        int opcao;

        do {

            mostrarMenuPrincipal();
            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    menuProdutos();
                    break;

                case 2:
                    menuClientes();
                    break;

                case 3:
                    realizarVenda();
                    break;

                case 4:
                    menuEstoque();
                    break;

                case 5:
                    menuRelatorios();
                    break;

                case 0:
                    System.out.println("\nEncerrando sistema...");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // =====================================================
    // MENU PRINCIPAL
    // =====================================================

    public static void mostrarMenuPrincipal() {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("               MERCADO JAVA DEV               ");
        System.out.println("==============================================");

        System.out.println("1 - Produtos");
        System.out.println("2 - Clientes");
        System.out.println("3 - Caixa / Nova Venda");
        System.out.println("4 - Estoque");
        System.out.println("5 - Relatórios");
        System.out.println("0 - Sair");
        System.out.println("==============================================");
    }

    // =====================================================
    // MENU PRODUTOS
    // =====================================================

    public static void menuProdutos() {

        int opcao;

        do {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("                 PRODUTOS");
            System.out.println("==============================================");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto");
            System.out.println("4 - Alterar produto");
            System.out.println("5 - Remover produto");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");

            opcao = lerInt("Escolha: ");

            switch (opcao) {

                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    listarProdutos();
                    break;

                case 3:
                    buscarProduto();
                    break;

                case 4:
                    alterarProduto();
                    break;

                case 5:
                    removerProduto();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // =====================================================
    // CADASTRAR PRODUTO
    // =====================================================

    public static void cadastrarProduto() {

        System.out.println("\n========== CADASTRAR PRODUTO ==========");

        String nome = lerTexto("Nome do produto: ");

        String categoria = lerTexto("Categoria: ");

        double preco = lerDouble("Preço: R$ ");

        int estoque = lerInt("Quantidade em estoque: ");

        Produto produto = mercado.cadastrarProduto(
                nome,
                categoria,
                preco,
                estoque
        );

        System.out.println("\nProduto cadastrado com sucesso!");

        System.out.println(produto);
    }

    // =====================================================
    // LISTAR PRODUTOS
    // =====================================================

    public static void listarProdutos() {

        System.out.println("\n========== PRODUTOS ==========");

        if (mercado.listarProdutos().isEmpty()) {

            System.out.println("Nenhum produto cadastrado.");

            return;
        }

        for (Produto produto : mercado.listarProdutos()) {

            System.out.println(produto);
        }
    }

    // =====================================================
    // BUSCAR PRODUTO
    // =====================================================

    public static void buscarProduto() {

        System.out.println("\n========== BUSCAR PRODUTO ==========");

        int codigo = lerInt("Código do produto: ");

        Produto produto = mercado.buscarProduto(codigo);

        if (produto == null) {

            System.out.println("Produto não encontrado!");

        } else {

            System.out.println("\nProduto encontrado:");
            System.out.println(produto);
        }
    }

    // =====================================================
    // ALTERAR PRODUTO
    // =====================================================

    public static void alterarProduto() {

        System.out.println("\n========== ALTERAR PRODUTO ==========");

        int codigo = lerInt("Código do produto: ");

        Produto produto = mercado.buscarProduto(codigo);

        if (produto == null) {

            System.out.println("Produto não encontrado!");

            return;
        }

        System.out.println("\nProduto atual:");
        System.out.println(produto);

        String nome = lerTexto("Novo nome: ");

        String categoria = lerTexto("Nova categoria: ");

        double preco = lerDouble("Novo preço: R$ ");

        int estoque = lerInt("Novo estoque: ");

        boolean alterado = mercado.alterarProduto(
                codigo,
                nome,
                categoria,
                preco,
                estoque
        );

        if (alterado) {

            System.out.println("\nProduto alterado com sucesso!");

        } else {

            System.out.println("\nErro ao alterar produto!");
        }
    }

    // =====================================================
    // REMOVER PRODUTO
    // =====================================================

    public static void removerProduto() {

        System.out.println("\n========== REMOVER PRODUTO ==========");

        int codigo = lerInt("Código do produto: ");

        Produto produto = mercado.buscarProduto(codigo);

        if (produto == null) {

            System.out.println("Produto não encontrado!");

            return;
        }

        System.out.println("\nProduto:");
        System.out.println(produto);

        String confirmacao =
                lerTexto("Deseja realmente remover? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {

            boolean removido =
                    mercado.removerProduto(codigo);

            if (removido) {

                System.out.println(
                        "Produto removido com sucesso!"
                );

            } else {

                System.out.println(
                        "Não foi possível remover."
                );
            }

        } else {

            System.out.println("Operação cancelada.");
        }
    }

    // =====================================================
    // MENU CLIENTES
    // =====================================================

    public static void menuClientes() {

        int opcao;

        do {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("                  CLIENTES");
            System.out.println("==============================================");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Buscar cliente");
            System.out.println("4 - Alterar cliente");
            System.out.println("5 - Remover cliente");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");

            opcao = lerInt("Escolha: ");

            switch (opcao) {

                case 1:
                    cadastrarCliente();
                    break;

                case 2:
                    listarClientes();
                    break;

                case 3:
                    buscarCliente();
                    break;

                case 4:
                    alterarCliente();
                    break;

                case 5:
                    removerCliente();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // =====================================================
    // CADASTRAR CLIENTE
    // =====================================================

    public static void cadastrarCliente() {

        System.out.println("\n========== CADASTRAR CLIENTE ==========");

        String nome = lerTexto("Nome: ");

        String cpf = lerTexto("CPF: ");

        String telefone = lerTexto("Telefone: ");

        Cliente cliente = mercado.cadastrarCliente(
                nome,
                cpf,
                telefone
        );

        System.out.println("\nCliente cadastrado!");

        System.out.println(cliente);
    }

    // =====================================================
    // LISTAR CLIENTES
    // =====================================================

    public static void listarClientes() {

        System.out.println("\n========== CLIENTES ==========");

        if (mercado.listarClientes().isEmpty()) {

            System.out.println("Nenhum cliente cadastrado.");

            return;
        }

        for (Cliente cliente : mercado.listarClientes()) {

            System.out.println(cliente);
        }
    }

    // =====================================================
    // BUSCAR CLIENTE
    // =====================================================

    public static void buscarCliente() {

        int codigo = lerInt("Código do cliente: ");

        Cliente cliente =
                mercado.buscarCliente(codigo);

        if (cliente == null) {

            System.out.println("Cliente não encontrado!");

        } else {

            System.out.println(cliente);
        }
    }

    // =====================================================
    // ALTERAR CLIENTE
    // =====================================================

    public static void alterarCliente() {

        int codigo = lerInt("Código do cliente: ");

        Cliente cliente =
                mercado.buscarCliente(codigo);

        if (cliente == null) {

            System.out.println("Cliente não encontrado!");

            return;
        }

        System.out.println("\nCliente atual:");
        System.out.println(cliente);

        String nome = lerTexto("Novo nome: ");

        String cpf = lerTexto("Novo CPF: ");

        String telefone = lerTexto("Novo telefone: ");

        boolean alterado =
                mercado.alterarCliente(
                        codigo,
                        nome,
                        cpf,
                        telefone
                );

        if (alterado) {

            System.out.println(
                    "Cliente alterado com sucesso!"
            );
        }
    }

    // =====================================================
    // REMOVER CLIENTE
    // =====================================================

    public static void removerCliente() {

        int codigo = lerInt("Código do cliente: ");

        Cliente cliente =
                mercado.buscarCliente(codigo);

        if (cliente == null) {

            System.out.println("Cliente não encontrado!");

            return;
        }

        System.out.println(cliente);

        String confirmacao =
                lerTexto("Remover cliente? (S/N): ");

        if (confirmacao.equalsIgnoreCase("S")) {

            mercado.removerCliente(codigo);

            System.out.println(
                    "Cliente removido!"
            );

        } else {

            System.out.println(
                    "Operação cancelada."
            );
        }
    }

    // =====================================================
    // NOVA VENDA
    // =====================================================

    public static void realizarVenda() {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("                 NOVA VENDA");
        System.out.println("==============================================");

        if (mercado.listarProdutos().isEmpty()) {

            System.out.println(
                    "Não existem produtos cadastrados."
            );

            return;
        }

        Cliente cliente = null;

        String usarCliente =
                lerTexto("Venda para cliente cadastrado? (S/N): ");

        if (usarCliente.equalsIgnoreCase("S")) {

            listarClientes();

            int codigoCliente =
                    lerInt("Código do cliente: ");

            cliente =
                    mercado.buscarCliente(codigoCliente);

            if (cliente == null) {

                System.out.println(
                        "Cliente não encontrado."
                );

                return;
            }
        }

        Venda venda =
                mercado.criarVenda(cliente);

        while (true) {

            System.out.println("\n------------------------------------------");
            System.out.println("CARRINHO");
            System.out.println("------------------------------------------");

            if (venda.getItens().isEmpty()) {

                System.out.println("Carrinho vazio.");

            } else {

                for (ItemVenda item : venda.getItens()) {

                    System.out.println(item);
                }

                System.out.println("------------------------------------------");

                System.out.printf(
                        "TOTAL: R$ %.2f%n",
                        venda.getTotal()
                );
            }

            System.out.println("\n1 - Adicionar produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Alterar quantidade");
            System.out.println("4 - Finalizar venda");
            System.out.println("0 - Cancelar venda");

            int opcao =
                    lerInt("Escolha: ");

            switch (opcao) {

                case 1:

                    listarProdutos();

                    int codigoProduto =
                            lerInt("Código do produto: ");

                    int quantidade =
                            lerInt("Quantidade: ");

                    boolean adicionado =
                            mercado.adicionarProdutoVenda(
                                    venda,
                                    codigoProduto,
                                    quantidade
                            );

                    if (adicionado) {

                        System.out.println(
                                "Produto adicionado ao carrinho!"
                        );

                    } else {

                        System.out.println(
                                "Não foi possível adicionar."
                        );
                    }

                    break;

                case 2:

                    int codigoRemover =
                            lerInt("Código do produto: ");

                    boolean removido =
                            mercado.removerProdutoVenda(
                                    venda,
                                    codigoRemover
                            );

                    if (removido) {

                        System.out.println(
                                "Produto removido!"
                        );

                    } else {

                        System.out.println(
                                "Produto não está no carrinho."
                        );
                    }

                    break;

                case 3:

                    int codigoAlterar =
                            lerInt("Código do produto: ");

                    int novaQuantidade =
                            lerInt("Nova quantidade: ");

                    boolean alterado =
                            mercado.alterarQuantidadeVenda(
                                    venda,
                                    codigoAlterar,
                                    novaQuantidade
                            );

                    if (alterado) {

                        System.out.println(
                                "Quantidade alterada!"
                        );

                    } else {

                        System.out.println(
                                "Não foi possível alterar."
                        );
                    }

                    break;

                case 4:

                    if (venda.getItens().isEmpty()) {

                        System.out.println(
                                "O carrinho está vazio!"
                        );

                        break;
                    }

                    finalizarPagamento(venda);

                    return;

                case 0:

                    System.out.println(
                            "Venda cancelada."
                    );

                    return;

                default:

                    System.out.println(
                            "Opção inválida!"
                    );
            }
        }
    }

    // =====================================================
    // PAGAMENTO
    // =====================================================

    public static void finalizarPagamento(Venda venda) {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("                  PAGAMENTO");
        System.out.println("==============================================");

        System.out.printf(
                "TOTAL: R$ %.2f%n",
                venda.getTotal()
        );

        System.out.println("\n1 - Dinheiro");
        System.out.println("2 - PIX");
        System.out.println("3 - Débito");
        System.out.println("4 - Crédito");

        int opcao =
                lerInt("Forma de pagamento: ");

        String formaPagamento;

        switch (opcao) {

            case 1:
                formaPagamento = "Dinheiro";
                break;

            case 2:
                formaPagamento = "PIX";
                break;

            case 3:
                formaPagamento = "Débito";
                break;

            case 4:
                formaPagamento = "Crédito";
                break;

            default:
                System.out.println(
                        "Forma de pagamento inválida."
                );
                return;
        }

        double valorPago;

        if (formaPagamento.equals("Dinheiro")) {

            valorPago =
                    lerDouble("Valor recebido: R$ ");

        } else {

            valorPago = venda.getTotal();
        }

        boolean finalizada =
                mercado.finalizarVenda(
                        venda,
                        formaPagamento,
                        valorPago
                );

        if (!finalizada) {

            System.out.println(
                    "\nPagamento não realizado!"
            );

            if (formaPagamento.equals("Dinheiro")) {

                System.out.println(
                        "O valor recebido é menor que o total."
                );
            }

            return;
        }

        System.out.println("\n==============================================");
        System.out.println("             VENDA FINALIZADA!");
        System.out.println("==============================================");

        System.out.printf(
                "Total: R$ %.2f%n",
                venda.getTotal()
        );

        System.out.println(
                "Pagamento: " + venda.getFormaPagamento()
        );

        if (formaPagamento.equals("Dinheiro")) {

            System.out.printf(
                    "Recebido: R$ %.2f%n",
                    venda.getValorPago()
            );

            System.out.printf(
                    "Troco: R$ %.2f%n",
                    venda.getTroco()
            );
        }

        System.out.println("==============================================");
    }

    // =====================================================
    // ESTOQUE
    // =====================================================

    public static void menuEstoque() {

        int opcao;

        do {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("                  ESTOQUE");
            System.out.println("==============================================");
            System.out.println("1 - Ver estoque");
            System.out.println("2 - Entrada de produtos");
            System.out.println("3 - Saída de produtos");
            System.out.println("4 - Produtos com estoque baixo");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");

            opcao = lerInt("Escolha: ");

            switch (opcao) {

                case 1:
                    listarProdutos();
                    break;

                case 2:
                    entradaEstoque();
                    break;

                case 3:
                    saidaEstoque();
                    break;

                case 4:
                    estoqueBaixo();
                    break;

                case 0:
                    break;

                default:
                    System.out.println(
                            "Opção inválida!"
                    );
            }

        } while (opcao != 0);
    }

    // =====================================================
    // ENTRADA ESTOQUE
    // =====================================================

    public static void entradaEstoque() {

        listarProdutos();

        int codigo =
                lerInt("Código do produto: ");

        int quantidade =
                lerInt("Quantidade de entrada: ");

        boolean sucesso =
                mercado.entradaEstoque(
                        codigo,
                        quantidade
                );

        if (sucesso) {

            System.out.println(
                    "Estoque atualizado!"
            );

        } else {

            System.out.println(
                    "Não foi possível atualizar."
            );
        }
    }

    // =====================================================
    // SAÍDA ESTOQUE
    // =====================================================

    public static void saidaEstoque() {

        listarProdutos();

        int codigo =
                lerInt("Código do produto: ");

        int quantidade =
                lerInt("Quantidade de saída: ");

        boolean sucesso =
                mercado.saidaEstoque(
                        codigo,
                        quantidade
                );

        if (sucesso) {

            System.out.println(
                    "Saída realizada!"
            );

        } else {

            System.out.println(
                    "Estoque insuficiente ou produto inválido."
            );
        }
    }

    // =====================================================
    // ESTOQUE BAIXO
    // =====================================================

    public static void estoqueBaixo() {

        System.out.println(
                "\n========== ESTOQUE BAIXO =========="
        );

        boolean encontrou = false;

        for (Produto produto : mercado.listarProdutos()) {

            if (produto.getEstoque() <= 5) {

                System.out.println(produto);

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println(
                    "Nenhum produto com estoque baixo."
            );
        }
    }

    // =====================================================
    // RELATÓRIOS
    // =====================================================

    public static void menuRelatorios() {

        int opcao;

        do {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("                RELATÓRIOS");
            System.out.println("==============================================");
            System.out.println("1 - Todas as vendas");
            System.out.println("2 - Faturamento");
            System.out.println("3 - Quantidade de produtos");
            System.out.println("4 - Quantidade de clientes");
            System.out.println("0 - Voltar");
            System.out.println("==============================================");

            opcao = lerInt("Escolha: ");

            switch (opcao) {

                case 1:

                    listarVendas();

                    break;

                case 2:

                    System.out.printf(
                            "\nFaturamento: R$ %.2f%n",
                            mercado.calcularFaturamento()
                    );

                    break;

                case 3:

                    System.out.println(
                            "\nProdutos cadastrados: "
                                    + mercado.listarProdutos().size()
                    );

                    break;

                case 4:

                    System.out.println(
                            "\nClientes cadastrados: "
                                    + mercado.listarClientes().size()
                    );

                    break;

                case 0:
                    break;

                default:

                    System.out.println(
                            "Opção inválida!"
                    );
            }

        } while (opcao != 0);
    }

    // =====================================================
    // LISTAR VENDAS
    // =====================================================

    public static void listarVendas() {

        System.out.println(
                "\n========== VENDAS =========="
        );

        if (mercado.listarVendas().isEmpty()) {

            System.out.println(
                    "Nenhuma venda realizada."
            );

            return;
        }

        for (Venda venda : mercado.listarVendas()) {

            System.out.println(venda);
        }
    }

    // =====================================================
    // LEITURA DE DADOS
    // =====================================================

    public static String lerTexto(String mensagem) {

        System.out.print(mensagem);

        return scanner.nextLine();
    }

    public static int lerInt(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um número inteiro válido!"
                );
            }
        }
    }

    public static double lerDouble(String mensagem) {

        while (true) {

            try {

                System.out.print(mensagem);

                String valor =
                        scanner.nextLine().replace(",", ".");

                return Double.parseDouble(valor);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Digite um valor válido!"
                );
            }
        }
    }
}