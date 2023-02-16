public class Pedido {
    private Integer idPedido;
    private Integer idCliente;
    private Integer idCarrinho;

    public void calculaTotal(){};
    public void adicionaProduto(Integer idProduto, Integer quantidade){};
    public void retiraProduto(Integer idProduto, Integer quantidade){};
    public void finalizaPedido(Integer idPedido){};
    public void statsPagamento(Integer idPedido){};
}
