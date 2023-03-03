public class VideoGame extends Jogo{
    private String paisDeOrigem;
    private String modelo;

    public VideoGame(String nomeJogo, String categoria, String fornecedor, char genero, float preco) {
        super(nomeJogo, categoria, fornecedor, genero, preco);
    }

    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
