// Classe Produto: representa um item que será vendido na loja
public class Produto {

    // Código único que identifica o produto
    int codigo;

    // Nome do produto (ex: Refrigerante, Camisa, Arroz)
    String nome;

    // Categoria do produto (ex: Bebidas, Roupas, Limpeza)
    String categoria;

    // Preço unitário do produto
    double preco;

    // Quantidade disponível em estoque
    int estoque;

    // Construtor da classe Produto
    // Ele é executado quando criamos um novo produto
    Produto(int c, String n, String cat, double p, int e) {
        codigo = c;      // recebe o código enviado
        nome = n;        // recebe o nome enviado
        categoria = cat; // recebe a categoria enviada
        preco = p;       // recebe o preço enviado
        estoque = e;     // recebe o estoque enviado
    }

    // toString(): define como o produto aparece quando é impresso na tela
    @Override
    public String toString() {
        return codigo + " - " + nome + " (" + categoria + ") | R$ " + preco + " | Estoque: " + estoque;
    }
}
