package pagamento;

public interface Pagamento {
    void processar(double valor);
    String obterStatus();
}
