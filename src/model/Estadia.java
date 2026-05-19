package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Estadia {
    private Veiculo veiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valor_pago;

    private int idEstadia;

    public Estadia(){}

    public Estadia(Veiculo veiculo, LocalDateTime entrada) {
        this.veiculo = veiculo;
        this.entrada = entrada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public int getIdEstadia() {
        return idEstadia;
    }

    public double getValor_pago() {
        return valor_pago;
    }

    public void setIdEstadia(int idGerado) {
        this.idEstadia = idGerado;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public double calcularValor(LocalDateTime saida){
        if(entrada == null){
            throw new IllegalStateException("A entrada ainda não foi registrada.");
        }

        if(saida == null) {
            throw new IllegalStateException("A saída ainda não foi registrada.");
        }

        long minutos = Duration.between(entrada, saida).toMinutes();
        long horas = (long) Math.ceil(minutos / 60.0);

        return horas * 10.0;
    }

}
