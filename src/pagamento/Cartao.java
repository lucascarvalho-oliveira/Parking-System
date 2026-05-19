package pagamento;

public class Cartao implements Pagamento{

    @Override public void processar(double valor){
        System.out.println("Enviando cobrança de R$ " + valor);
        System.out.println("Conectando com o banco...");
        System.out.println("[Sucesso] Transação de Crédito Aprovada!");
    }

    @Override public String obterStatus() {
        return "CARTAO_APROVADO";
    }
}
