package application;

import application.controller.CadastroController;
import application.controller.CalculoController;
import application.controller.RegistroController;
import repository.EstadiaRepository;
import repository.PessoaRepository;
import repository.RelatorioRepository;
import repository.VeiculoRepository;
import service.EstadiaService;
import service.PessoaService;
import service.VeiculoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        VeiculoRepository repositoryVeiculo = new VeiculoRepository();
        EstadiaRepository repositoryEstadia = new EstadiaRepository();

        boolean sair = false;
        do{
            System.out.println("==================== Estacionamento ====================");
            System.out.println("1 - Cadastrar Carro:");
            System.out.println("2 - Registrar entrada");
            System.out.println("3 - Calcular Saida:");
            System.out.println("4 - Mostrar Relatório:");
            System.out.println("5 - Fechar Programa:");
            int menu = sc.nextInt();sc.nextLine();
            System.out.println();

            switch (menu) {
                case 1:
                    PessoaRepository repositoryPessoa = new PessoaRepository();

                    PessoaService servicePessoa = new PessoaService(repositoryPessoa);
                    VeiculoService serviceVeiculo = new VeiculoService(repositoryVeiculo);

                    CadastroController controllerCadastro = new CadastroController(servicePessoa, serviceVeiculo);
                    controllerCadastro.cadastro(sc);
                    break;

                case 2:
                    EstadiaService serviceEstadia = new EstadiaService(repositoryEstadia);

                    RegistroController controllerRegistro = new RegistroController(repositoryVeiculo, serviceEstadia);
                    controllerRegistro.registrarEntrada(sc);
                    break;

                case 3:
                    CalculoController controllerCalculo = new CalculoController(repositoryEstadia, repositoryVeiculo);

                    controllerCalculo.calculoEstadia(sc);
                    break;

                case 4:
                    RelatorioRepository repositoryRelatorio = new RelatorioRepository();

                    repositoryRelatorio.Relatorio();
                    break;

                case 5:
                    System.out.println("Programa Finalizado!");
                    sair = true;
                    break;

                default:
                    System.out.println("\nOpção inválida!\n");
                    break;
            }
        }while(!sair);

        sc.close();
    }
}
