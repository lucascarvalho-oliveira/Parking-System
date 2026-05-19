package pagamento;

public class Pix implements Pagamento{

    @Override public void processar(double valor){
        System.out.println("Gerando QR Code dinâmico...");
        System.out.println("Código PIX: ????");
        System.out.println("[Sucesso] PIX recebido e confirmado!");
    }

    @Override public String obterStatus() {
        return "PIX_PAGO";
    }

}
