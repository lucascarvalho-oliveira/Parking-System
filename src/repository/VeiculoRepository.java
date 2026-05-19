package repository;

import database.Conexao;
import model.Veiculo;
import model.enums.TipoMotor;
import model.enums.TipoVeiculo;

import java.sql.*;

public class VeiculoRepository {

    public void salvarVeiculo(Veiculo veiculo){
        String sql = "INSERT INTO veiculo(placa, cor, modelo, tipo_motor, tipo_veiculo, idPessoa_fk) VALUES(?, ?, ?, ?, ?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getCor());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getTipomotor().name());
            stmt.setString(5, veiculo.getTipoveiculo().name());
            stmt.setInt(6, veiculo.getPessoa().getIdPessoa());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    veiculo.setIdVeiculo(idGerado);
                    System.out.println("\nVeiculo salvo com sucesso!\n");
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao salvar veiculo.");
            e.printStackTrace();
        }
    }

    public Veiculo buscarPlaca(String placa){
        String sql = "SELECT * FROM veiculo WHERE placa = ?";

        try( Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Veiculo veiculo = new Veiculo();

                veiculo.setIdVeiculo(rs.getInt("idVeiculo"));

                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setModelo(rs.getString("modelo"));

                TipoMotor tipoMotor = TipoMotor.valueOf(rs.getString("tipo_motor"));
                veiculo.setTipomotor(tipoMotor);
                TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(rs.getString("tipo_veiculo"));
                veiculo.setTipoveiculo(tipoVeiculo);

                return veiculo;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
