package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {
    
    // Configurações do banco de dados
    private static Dotenv dotenv = Dotenv.load();
    private static String dbUrl = dotenv.get("DB_URL");
    private static String dbUser = dotenv.get("DB_USER");
    private static String dbPassword = dotenv.get("DB_PASSWORD");

    //"jdbc:mysql://localhost:3306/db_vvv?useSSL=false&serverTimezone=UTC"

    //conexão com o banco de dados
    public static Connection getConnection() {
        try {
            // Registrar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver do MySQL não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
            return null;
        }
    }
}