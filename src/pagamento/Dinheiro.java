package pagamento;

import java.util.Scanner;

public class Dinheiro implements Pagamento{

    @Override public void processar(double valor) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Valor a receber: R$ " + valor);
        System.out.println("Valor recebido");
        double valorRecebido = sc.nextDouble();

        if(valorRecebido >= valor){
            double troco = valorRecebido - valor;

            System.out.println("Troco = " + troco);
        }
    }

    @Override
    public String obterStatus() {
        return "PAGO_EM_DINHEIRO";
    }
}
