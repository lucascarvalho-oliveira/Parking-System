package application.controller;

import pagamento.Pagamento;
import pagamento.Pix;
import pagamento.Cartao;
import pagamento.Dinheiro;
import model.Estadia;
import model.Veiculo;
import repository.EstadiaRepository;
import repository.VeiculoRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CalculoController {
    private EstadiaRepository repositoryEstadia;
    private VeiculoRepository repositoryVeiculo;

    public CalculoController(EstadiaRepository repositoryEstadia, VeiculoRepository repositoryVeiculo){
        this.repositoryEstadia = repositoryEstadia;
        this.repositoryVeiculo = repositoryVeiculo;
    }

    public void calculoEstadia(Scanner sc){
        System.out.println("Informe a placa do veiculo:");
        String placa = sc.nextLine().trim().toUpperCase();;

        try {
            Veiculo veiculo = repositoryVeiculo.buscarPlaca(placa);
            Estadia estadia = repositoryEstadia.buscarEstadia(veiculo.getIdVeiculo());

            LocalDateTime saida = LocalDateTime.now();

            if(estadia == null){
                System.out.println("\nErro: Não foi possível calcular o valor, estadia não encontrada no sistema!\n");
                return;
            }

            double valor = estadia.calcularValor(saida);

            System.out.println("Valor a pagar " + valor);

            System.out.println("\nEscolha o método de pagamento");
            System.out.println("1 - Pix:");
            System.out.println("2 - Cartão:");
            System.out.println("3 - Dinheiro:");
            int menu = sc.nextInt();sc.nextLine();
            System.out.println();

            Pagamento formaEscolhida = null;

            switch (menu){
                case 1:
                    formaEscolhida = new Pix();
                    break;
                case 2:
                    formaEscolhida = new Cartao();
                    break;
                case 3:
                    formaEscolhida = new Dinheiro();
                    break;
                default:
                    System.out.println("\nOpção inválida!\n");
                    break;
            }

            if(formaEscolhida != null){
                formaEscolhida.processar(valor);
                System.out.println("\nStatus do pagamento: " + formaEscolhida.obterStatus());

                estadia.setVeiculo(veiculo);
                estadia.setSaida(saida);
                estadia.setValor_pago(valor);
                repositoryEstadia.salvarEstadia(estadia);
            }

        }catch (IllegalArgumentException e){
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }
}
