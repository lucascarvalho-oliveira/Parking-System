package repository;

import database.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RelatorioRepository {

    public void Relatorio(){
        String sql = "SELECT DATE_FORMAT(e.data_entrada, '%m/%Y') AS mes_ano," +
                     "v.tipo_veiculo AS tipo_carro, " +
                     "COUNT(*) AS quantidade, " +
                     "SUM(e.valor_pago) AS total_ganho " +
                     "FROM estadia e " +
                     "INNER JOIN veiculo v ON e.idVeiculo_fk = v.idVeiculo " +
                     "WHERE e.data_saida IS NOT NULL " +
                     "GROUP BY DATE_FORMAT(e.data_entrada, '%m/%Y'), v.tipo_veiculo " +
                     "ORDER BY mes_ano DESC, total_ganho DESC";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){
            System.out.println("==================== HISTÓRICO FINANCEIRO POR TIPO ====================");
            System.out.printf("%-10s | %-15s | %-12s | %-15s\n", "MÊS/ANO", "TIPO VEICULO", "QTD VEICULO", "FATURAMENTO");
            System.out.println("-----------------------------------------------------------------------");

            boolean temDados = false;
            while(rs.next()){
                temDados = true;
                String mesAno = rs.getString("mes_ano");
                String tipoCarro = rs.getString("tipo_carro");
                int quantidade = rs.getInt("quantidade");
                double totalGanho = rs.getDouble("total_ganho");

                System.out.printf("%-10s | %-15s | %-12d | R$ %,.2f\n", mesAno, tipoCarro, quantidade, totalGanho);
            }

            if(!temDados){
                System.out.println("Nenhum histórico encontrado para veículos finalizados.");
            }
            System.out.println("=======================================================================\n");

        }catch (SQLException e) {
            System.out.println("Erro ao gerar histórico.");
            e.printStackTrace();
        }


    }
}
