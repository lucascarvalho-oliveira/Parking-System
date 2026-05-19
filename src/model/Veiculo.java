package model;

import model.enums.TipoMotor;
import model.enums.TipoVeiculo;

public class Veiculo {
    private String placa;
    private String cor;
    private String modelo;
    private TipoMotor tipomotor;
    private TipoVeiculo tipoveiculo;

    private Pessoa pessoa;
    private int idVeiculo;

    public Veiculo(){}

    public Veiculo(String placa, String cor, String modelo, TipoMotor tipomotor, TipoVeiculo tipoveiculo, Pessoa pessoa) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;

        this.tipomotor = tipomotor;
        this.tipoveiculo = tipoveiculo;
        this.pessoa = pessoa;
    }

    public String getPlaca() {
        return placa;
    }

    public String getCor() {
        return cor;
    }

    public String getModelo() {
        return modelo;
    }

    public TipoMotor getTipomotor() {
        return tipomotor;
    }

    public TipoVeiculo getTipoveiculo() {
        return tipoveiculo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getIdVeiculo(){
        return idVeiculo;
    }

    public void setIdVeiculo(int idGerado){
        this.idVeiculo = idGerado;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipomotor(TipoMotor tipomotor) {
        this.tipomotor = tipomotor;
    }

    public void setTipoveiculo(TipoVeiculo tipoveiculo) {
        this.tipoveiculo = tipoveiculo;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "tipoveiculo=" + tipoveiculo +
                "placa='" + placa + '\'' +
                ", cor='" + cor + '\'' +
                ", modelo='" + modelo + '\'' +
                ", tipomotor=" + tipomotor +
                '}';
    }
}
