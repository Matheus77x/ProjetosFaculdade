import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        // Lista onde os produtos cadastrados serão armazenados
        ArrayList<Produto> produtos = new ArrayList<>();

        // Lista simples com nome + CPF dos clientes
        ArrayList<String> clientes = new ArrayList<>();

        // Lista que guarda o resumo das vendas realizadas
        ArrayList<String> historicoVendas = new ArrayList<>();

        // Scanner para ler o teclado
        Scanner sc = new Scanner(System.in);

        int op = -1; // variável usada para controlar o menu

        // Loop principal do sistema (funciona até o usuário escolher 0)
        while (op != 0) {

            // Mostra o menu de opções
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Listar Clientes");
            System.out.println("5 - Realizar Venda");
            System.out.println("6 - Histórico de Vendas");
            System.out.println("0 - Sair");

            System.out.print("Opção: ");
            op = sc.nextInt(); // lê a opção digitada
            sc.nextLine();     // limpa a linha pendente

            switch (op) {

                // ---------------------------------------------------
                // 1 - CADASTRAR PRODUTO
                // ---------------------------------------------------
                case 1:
                    System.out.print("Código: ");
                    int cod = sc.nextInt();
                    sc.nextLine(); // limpa buffer

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();

                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();

                    // Se o preço for negativo, corrige para 0
                    if (preco < 0) {
                        System.out.println("Preço não pode ser negativo! Definido como 0.");
                        preco = 0;
                    }

                    System.out.print("Estoque: ");
                    int est = sc.nextInt();

                    // Se o estoque for negativo, corrige para 0
                    if (est < 0) {
                        System.out.println("Estoque não pode ser negativo! Definido como 0.");
                        est = 0;
                    }

                    // Cria o produto e adiciona na lista
                    produtos.add(new Produto(cod, nome, categoria, preco, est));
                    System.out.println("Produto cadastrado!");
                    break;

                // ---------------------------------------------------
                // 2 - LISTAR PRODUTOS
                // ---------------------------------------------------
                case 2:
                    // Se não existir produto, avisa
                    if (produtos.size() == 0) {
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }

                    // Mostra todos os produtos da lista
                    for (Produto p : produtos) {
                        System.out.println(p);
                    }
                    break;

                // ---------------------------------------------------
                // 3 - CADASTRAR CLIENTE
                // ---------------------------------------------------
                case 3:
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = sc.nextLine();  // lê normalmente

                // evita adicionar nome vazio (causado pelo nextInt anterior)
                while (nomeCliente.trim().isEmpty()) {
                    System.out.print("Digite um nome válido: ");
                    nomeCliente = sc.nextLine();
                    }

                    clientes.add(nomeCliente); // apenas o nome agora
                    System.out.println("Cliente cadastrado!");
                    break;


                // ---------------------------------------------------
                // 4 - LISTAR CLIENTES
                // ---------------------------------------------------
                case 4:
                    // Se lista vazia, avisa
                    if (clientes.size() == 0) {
                        System.out.println("Nenhum cliente cadastrado.");
                        break;
                    }

                    // Exibe cada cliente cadastrado
                    for (String c : clientes) {
                        System.out.println(c);
                    }
                    break;

                // ---------------------------------------------------
                // 5 - REALIZAR VENDA
                // ---------------------------------------------------
                case 5:
                    // Verifica se o sistema tem clientes e produtos
                    if (clientes.size() == 0 || produtos.size() == 0) {
                        System.out.println("Cadastre clientes e produtos antes.");
                        break;
                    }

                    // Mostra lista de clientes
                    System.out.println("Clientes cadastrados:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + " - " + clientes.get(i));
                    }

                    // Usuário escolhe o cliente
                    System.out.print("Escolha o cliente: ");
                    int cli = sc.nextInt() - 1;

                    double total = 0; // total da venda
                    String resumo = "Cliente: " + clientes.get(cli) + "\n"; // resumo da venda

                    // Loop para adicionar produtos à venda
                    while (true) {

                        System.out.print("Código do produto (0 para finalizar): ");
                        int codProd = sc.nextInt();

                        // Se digitar 0, encerra a venda
                        if (codProd == 0) break;

                        // Procura produto pelo código
                        Produto escolhido = null;
                        for (Produto p : produtos) {
                            if (p.codigo == codProd) escolhido = p;
                        }

                        // Se não encontrou produto
                        if (escolhido == null) {
                            System.out.println("Produto não existe!");
                            continue;
                        }

                        // Lê quantidade
                        System.out.print("Quantidade: ");
                        int q = sc.nextInt();

                        // Verifica estoque
                        if (q > escolhido.estoque) {
                            System.out.println("Estoque insuficiente!");
                            continue;
                        }

                        // Diminui do estoque
                        escolhido.estoque -= q;

                        // Calcula subtotal
                        double subtotal = escolhido.preco * q;
                        total += subtotal;

                        // Adiciona no resumo
                        resumo += " - " + escolhido.nome + " x" + q + " = R$ " + subtotal + "\n";
                    }

                    // Finalização da venda
                    resumo += "TOTAL = R$ " + total;
                    historicoVendas.add(resumo);

                    System.out.println("Venda finalizada!");
                    System.out.println(resumo);
                    break;

                // ---------------------------------------------------
                // 6 - HISTÓRICO DE VENDAS
                // ---------------------------------------------------
                case 6:
                    // Se nenhuma venda foi feita
                    if (historicoVendas.size() == 0) {
                        System.out.println("Nenhuma venda realizada.");
                    } 
                    else {
                        // Mostra cada venda registrada
                        for (String venda : historicoVendas) {
                            System.out.println("\n--- VENDA ---");
                            System.out.println(venda);
                        }
                    }
                    break;

                // ---------------------------------------------------
                // OPÇÃO INVÁLIDA
                // ---------------------------------------------------
                default:
                    System.out.println("Opção inválida!");
            }
        }

        // Fecha o scanner
        sc.close();
    }
}
