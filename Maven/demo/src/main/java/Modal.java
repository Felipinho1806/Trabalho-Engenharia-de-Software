import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.Conexao;

public class Modal {
    private int idProprietario;
    private int capacidade;
    private String tipo;
    private String categoria;
    private String marca;

    public int getIdProprietario() {
        return idProprietario;
    }
    public void setIdProprietario(int idProprietario) {
        this.idProprietario = idProprietario;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int cadastroModal(int capacidade, String tipo, String categoria, String marca) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            String inserirSQL = "INSERT INTO modal (capacidade, tipo, categoria, marca) VALUES (?, ?, ?, ?)";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(inserirSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Definir os valores dos parâmetros
            pstmt.setInt(1, capacidade);
            pstmt.setString(2, tipo);
            pstmt.setString(3, categoria);
            pstmt.setString(4, marca);

            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
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

    public int excluirModal(int id_modal){
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String excluirSQL = "DELETE FROM modal WHERE id_modal = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(excluirSQL);
            pstmt.setInt(1, id_modal);
            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Modal excluido");    
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