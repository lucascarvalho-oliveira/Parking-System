package application.controller;

import model.Estadia;
import model.Veiculo;
import repository.EstadiaRepository;
import repository.VeiculoRepository;
import service.EstadiaService;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RegistroController {
    private VeiculoRepository repositoryVeiculo;
    private EstadiaService serviceEstadia;

    public RegistroController(VeiculoRepository repositoryVeiculo, EstadiaService serviceEstadia) {
        this.repositoryVeiculo = repositoryVeiculo;
        this.serviceEstadia = serviceEstadia;
    }

    public void registrarEntrada(Scanner sc){
        System.out.println("Informe a placa do veiculo:");
        String placa = sc.nextLine().trim().toUpperCase();;

        Veiculo veiculo = repositoryVeiculo.buscarPlaca(placa);

        if(veiculo != null){
            Estadia estadia = new Estadia(veiculo, LocalDateTime.now());

            serviceEstadia.salvarEstadia(estadia);
        }else{
            System.out.println("\nVeículo não encontrado!\n");
        }
    }
}
