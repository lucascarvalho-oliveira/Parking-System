package service;

import model.Estadia;
import repository.EstadiaRepository;

public class EstadiaService {
    private EstadiaRepository repository;

    public EstadiaService(EstadiaRepository repository){
        this.repository = repository;
    }

    public void salvarEstadia(Estadia estadia){
        if(estadia.getEntrada() == null){
            throw new IllegalArgumentException("Hora invalida!");
        }

        repository.salvarEstadia(estadia);
    }
}
