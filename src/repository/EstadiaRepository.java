package repository;

import database.Conexao;
import model.Estadia;

import java.sql.*;

public class EstadiaRepository {
    public void salvarEstadia(Estadia estadia){

        if(estadia.getIdEstadia() == 0) {
            String sql = "INSERT INTO estadia(data_entrada, idVeiculo_fk) VALUES(?, ?)";

            try (Connection conn = new Conexao().conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            ) {
                stmt.setTimestamp(1, Timestamp.valueOf(estadia.getEntrada()));
                stmt.setInt(2, estadia.getVeiculo().getIdVeiculo());

                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        estadia.setIdEstadia(idGerado);
                        System.out.println("\nVeiculo Registrado com sucesso!\n");
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erro ao salvar estadia.");
                e.printStackTrace();
            }

        }else {

            String sql = "UPDATE estadia SET data_saida = ?, valor_pago = ? WHERE idEstadia = ?";

            try(Connection conn = new Conexao().conectar();
                PreparedStatement stmt = conn.prepareStatement(sql);
            ){
                stmt.setTimestamp(1, Timestamp.valueOf(estadia.getSaida()));
                stmt.setDouble(2, estadia.getValor_pago());
                stmt.setInt(3, estadia.getIdEstadia());

                stmt.executeUpdate();
                System.out.println("Estadia atualizada com sucesso!");
                System.out.println();

            }catch (SQLException e) {
                System.out.println("Erro ao salvar estadia.");
                e.printStackTrace();
            }

        }
    }

    public Estadia buscarEstadia(int idVeiculo){
        String sql = "SELECT * FROM estadia WHERE idVeiculo_fk = ? AND data_saida IS NULL";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setInt(1, idVeiculo);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Estadia estadia = new Estadia();

                estadia.setIdEstadia(rs.getInt("idEstadia"));
                estadia.setEntrada(rs.getTimestamp("data_entrada").toLocalDateTime());

                return estadia;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
