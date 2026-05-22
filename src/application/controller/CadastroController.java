package application.controller;

import model.Pessoa;
import model.Veiculo;
import model.enums.TipoMotor;
import model.enums.TipoVeiculo;
import service.PessoaService;
import service.VeiculoService;

import java.util.Scanner;

public class CadastroController {
    private PessoaService servicePessoa;
    private VeiculoService serviceVeiculo;

    public CadastroController(PessoaService servicePessoa, VeiculoService serviceVeiculo) {
        this.servicePessoa = servicePessoa;
        this.serviceVeiculo = serviceVeiculo;
    }

    public void cadastro(Scanner sc) {
        try {
            System.out.println("================= Informação do cliente =================");
            System.out.println("Nome do cliente:");
            String nome = sc.nextLine().toUpperCase();
            System.out.println("Telefone de contado. (DD)9.XXXX-XXXX:\n");
            String telefone = sc.nextLine().trim();
            System.out.println();

            Pessoa pessoa = new Pessoa(nome, telefone);
            servicePessoa.salvarPessoa(pessoa);

            System.out.println("================= Informação do Veiculo =================");
            System.out.println("Placa do veiculo:");
            String placa = sc.nextLine().trim().toUpperCase();
            ;
            System.out.println("Cor do veiculo:");
            String cor = sc.nextLine().trim();
            System.out.println("Modelo do veiculo:");
            String modelo = sc.nextLine().trim();

            Veiculo veiculo = new Veiculo(placa, cor, modelo, escolherTipoMotor(sc), escolherTipoVeiculo(sc), pessoa);
            serviceVeiculo.salvarVeiculo(veiculo);

        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    public TipoMotor escolherTipoMotor(Scanner sc) {
        while (true){
            System.out.println("Escolha qual o tipo do motor:");
            System.out.println("1 - Combustão | 2 - Elétrico");
            int menu = sc.nextInt();sc.nextLine();

            if (menu == 1)return TipoMotor.combustao;
            if (menu == 2) return TipoMotor.eletrico;
            System.out.println("\nNumero invalido!\n");
        }
    }

    public TipoVeiculo escolherTipoVeiculo(Scanner sc) {
        while (true){
            System.out.println("Escolha o tipo do veiculo:");
            System.out.println("1 - Carro popular | 2 - Van | 3 - Moto");
            int menu_3 = sc.nextInt();sc.nextLine();

            switch (menu_3) {
                case 1: return TipoVeiculo.popular;
                case 2: return TipoVeiculo.van;
                case 3: return TipoVeiculo.moto;
                default: System.out.println("\nNumero invalido!\n");
            }
        }
    }

}
