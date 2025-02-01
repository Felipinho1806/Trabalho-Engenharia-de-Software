import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.Conexao;

public class Local {
    public String nome;
    public String endereco;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

      public int cadastrolocal( String nome, String endereco) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            String inserirSQL = "INSERT INTO local (nome, endereco) VALUES (?, ?)";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(inserirSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Definir os valores dos parâmetros
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);

            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Local cadastrado");
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

    public int excluirLocal(int id_local){
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String excluirSQL = "DELETE FROM local WHERE id_local = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(excluirSQL);
            pstmt.setInt(1, id_local);
            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Local excluido");    
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

