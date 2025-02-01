import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.Conexao;

public class PontoDeVendas {
    public String nome;
    public String endereco;
    public String telefone;

    public PontoDeVendas() {

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && telefone.length() > 15) {
            throw new IllegalArgumentException("O telefone não pode ter mais que 15 caracteres.");
        }
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
      public int cadastroPontoDeVenda( String nome, String endereco, String telefone) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            String inserirSQL = "INSERT INTO pontodevendas (nome, endereco, telefone) VALUES (?, ?, ?)";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(inserirSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Definir os valores dos parâmetros
            pstmt.setString(1, nome);
            pstmt.setString(2, endereco);
            pstmt.setString(3, telefone);

            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Ponto de venda cadastrado");
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

    public int excluirPontoDeVenda(int id_pontoDeVendas){
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String excluirSQL = "DELETE FROM pontodevendas WHERE id_pontoDeVendas = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(excluirSQL);
            pstmt.setInt(1, id_pontoDeVendas);
            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // inserção foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Ponto de vendas excluido");    
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