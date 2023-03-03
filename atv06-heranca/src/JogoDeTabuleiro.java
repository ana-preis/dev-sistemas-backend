public class JogoDeTabuleiro extends Jogo {
    private int qtdPecas;

    public JogoDeTabuleiro(String nomeJogo, String categoria, String fornecedor, char genero, float preco) {
        super(nomeJogo, categoria, fornecedor, genero, preco);
    }

    public int getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(int qtdPecas) {
        this.qtdPecas = qtdPecas;
    }
}
