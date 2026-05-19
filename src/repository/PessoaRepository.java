package repository;

import database.Conexao;
import model.Pessoa;

import java.sql.*;

public class PessoaRepository {

    public void salvarPessoa(Pessoa pessoa){
        String sql = "INSERT INTO pessoa(nome, telefone) VALUES (?, ?)";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getTelefone());

            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()) {

                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    pessoa.setIdPessoa(idGerado);
                    System.out.println("\nPessoa salva com sucesso!\n");
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao salvar pessoa.");
            e.printStackTrace();
        }
    }

    public Pessoa buscaPessoa(String nome, String telefone){
        String sql = "SELECT idPessoa FROM pessoa WHERE nome = ? AND telefone = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1, nome);
            stmt.setString(2, telefone);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Pessoa pessoa = new Pessoa();

                    pessoa.setIdPessoa(rs.getInt("idPessoa"));

                    return pessoa;
                }
            }
        }catch (Exception e) {
            System.out.println("Erro ao buscar pessoa.");
            e.printStackTrace();
        }

        return null;
    }
}
