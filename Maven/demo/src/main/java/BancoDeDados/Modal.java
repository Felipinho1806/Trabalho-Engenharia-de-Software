package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.github.cdimascio.dotenv.Dotenv;

public class Modal {
    private int idProprietario;
    private int capacidade;
    private String tipo;
    private String categoria;
    private String marca;

    private static Dotenv dotenv = Dotenv.load();
    private static String dbUrl = dotenv.get("DB_URL");
    private static String dbUser = dotenv.get("DB_USER");
    private static String dbPassword = dotenv.get("DB_PASSWORD");

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

    public static int cadastroModal(int Capacidade, String Tipo, String Categoria, String Marca) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Carregar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conexao = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Criar a instrução SQL para inserir dados
            String inserirSQL = "INSERT INTO modal (capacidade, tipo, categoria, marca) VALUES (?, ?, ?, ?)";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(inserirSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            // Definir os valores dos parâmetros
            pstmt.setInt(1, Capacidade);
            pstmt.setString(2, Tipo);
            pstmt.setString(3, Categoria);
            pstmt.setString(4, Marca);

            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);  // Retorna o ID do novo registro inserido
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexao != null) conexao.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return -1; // Retorna -1 em caso de falha na inserção
    }

}
