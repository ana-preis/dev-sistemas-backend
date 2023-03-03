public class JogoDeCarta extends Jogo {
    private int qtdeCartas;

    public JogoDeCarta(String nomeJogo, String categoria, String fornecedor, char genero, float preco) {
        super(nomeJogo, categoria, fornecedor, genero, preco);
    }

    public int getQtdeCartas() {
        return qtdeCartas;
    }

    public void setQtdeCartas(int qtdeCartas) {
        this.qtdeCartas = qtdeCartas;
    }
}
