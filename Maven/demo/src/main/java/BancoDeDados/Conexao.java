package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {
    
    private static Dotenv dotenv = Dotenv.load();
    private static String dbUrl = dotenv.get("DB_URL");
    private static String dbUser = dotenv.get("DB_USER");
    private static String dbPassword = dotenv.get("DB_PASSWORD");

    public static int login(String email, String password) {

        try (Connection conexao = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {

            // Consulta SQL
            String sql = "SELECT * FROM usuario WHERE email = '" + email + "' AND senha = '" + password + "'";

            ResultSet resClient = conexao.createStatement().executeQuery(sql);
            if (resClient.next()) {
                System.out.println("Seja bem-vindo " + resClient.getString("nome"));
                return 0;
            } else {
                System.out.println("Dados incorretos.");
                return 1;
            }

            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao acessar o banco de dados: " + ex.getMessage());
                return 1;
            }

        }

    public static int cadastro(String Email, String Senha, String Nome) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Carregar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conexao = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String verificacaoEmail = "SELECT COUNT(*) FROM usuario WHERE Email = ?";
            pstmt = conexao.prepareStatement(verificacaoEmail);
            pstmt.setString(1, Email);
            rs = pstmt.executeQuery();

            rs.next();
            if(rs.getInt(1) > 0) {
                System.out.println("Esse email já está cadastrado!");
                return 1;

            } else {
                // Criar a instrução SQL para inserir dados
                String inserirSQL = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

                // Criar o PreparedStatement
                pstmt = conexao.prepareStatement(inserirSQL);

                // Definir os valores dos parâmetros
                pstmt.setString(1, Nome);  // 1º parâmetro
                pstmt.setString(2, Email); // 2º parâmetro
                pstmt.setString(3, Senha); // 3º parâmetro

                // Executar a instrução SQL
                int linhasAfetadas = pstmt.executeUpdate();

                // Exibir mensagem de sucesso
                if (linhasAfetadas > 0) {
                    System.out.println("Nova linha inserida com sucesso!");
                    return 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } 
        }
    return 0;
    
    }
}