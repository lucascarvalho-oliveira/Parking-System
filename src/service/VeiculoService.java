package service;

import model.Veiculo;
import repository.VeiculoRepository;

public class VeiculoService {
    private VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository){
        this.repository = repository;
    }

    public void salvarVeiculo(Veiculo veiculo){
        if(veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()){
            throw new IllegalArgumentException("placa invalida!");
        }

        if(veiculo.getCor() == null || veiculo.getCor().isBlank()){
            throw new IllegalArgumentException("Cor invalida!");
        }

        if(veiculo.getModelo() == null || veiculo.getModelo().isBlank()){
            throw new IllegalArgumentException("Modelo invalido!");
        }

        Veiculo veiculoExistente = repository.buscarPlaca(veiculo.getPlaca());

        if(veiculoExistente != null){
            System.out.println("Veículo já cadastrado no sistema.");
            return;
        }

        repository.salvarVeiculo(veiculo);
    }
}
