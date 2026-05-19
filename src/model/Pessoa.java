package model;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private String telefone;

    private int idPessoa;

    private List<Veiculo> veiculos;

    public Pessoa(){}

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

        this.veiculos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getIdPessoa(){
        return idPessoa;
    }

    public void setIdPessoa(int idGerado){
        this.idPessoa = idGerado;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
