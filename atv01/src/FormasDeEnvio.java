public class FormasDeEnvio {
    private Integer idFormasDeEnvio;
    private String formaDeEnvio;
    private Float valorPeso;

    public void pesoDosProdutos(Integer idPedido){};
    public void calcularFrete(Integer idPedido){};
    public void formasDeEnvio(){};
    public void valorFrete(Integer idPedido){};
    public void selecionarFormaDeEnvio(Integer idPedido, String formaDeEnvio){};
    public void statusEnvio(Integer idPedido){};
}
