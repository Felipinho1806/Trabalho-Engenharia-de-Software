import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.Conexao;

public class ProprietarioModal {
    private String email;
    private String telefone;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

        public int cadastroProprietarioModal( String email, String telefone) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            String inserirSQL = "INSERT INTO proprietariomodal (email, telefone) VALUES (?, ?)";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(inserirSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Definir os valores dos parâmetros
            pstmt.setString(1, email);
            pstmt.setString(2, telefone);

            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Proprietario de modal cadastrado");
                    return rs.getInt(1);  // Retorna o ID do novo registro inserido
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return -1; // Retorna -1 em caso de falha na inserção
    }

    public int excluirProprietarioModal(int id_proprietarioModal){
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String excluirSQL = "DELETE FROM proprietariomodal WHERE id_proprietarioModal = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(excluirSQL);
            pstmt.setInt(1, id_proprietarioModal);
            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Proprietario de modal excluido");    
                return 0;          
            } else{
                System.out.println("O id digitado não foi encontrado");
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Erro ao excluir
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}

