import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BancoDeDados.Conexao;
import io.github.cdimascio.dotenv.Dotenv;

public class Usuario {
    
    private String nome;
    private String email;
    private String senha;
    private int id;
    
    private static Dotenv dotenv = Dotenv.load();
    private static String dbUrl = dotenv.get("DB_URL");
    private static String dbUser = dotenv.get("DB_USER");
    private static String dbPassword = dotenv.get("DB_PASSWORD");

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void logar(String senha, String email) {
        if(senha.equals(getSenha()) && email.equals(getEmail())) {
            System.out.println("Usuário logado.");
        }
        else {
            System.out.println("Usuário inválido!");
        }
    }

    public void deslogar() {
        setEmail(null);
        setSenha(null);
        System.out.println("Cliente deslogado.");
    }

    /*public void cadastroCliente(String senha, String email) {
        setEmail(email);
        setSenha(senha);
        System.out.println("Cliente cadastrado.");
    } */

    public void solicitarReserva(String localReserva, int idReserva) {
        setId(idReserva);
        System.out.println("Reserva para " + localReserva + " feita, id da reserva: " + getId());
    }

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
                    System.out.println("Cadastro realizado com sucesso!");
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

     public int excluirUsuario(int id_usuario){
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String excluirSQL = "DELETE FROM usuario WHERE id_usuario = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(excluirSQL);
            pstmt.setInt(1, id_usuario);
            // Executar a instrução SQL
            int linhasAfetadas = pstmt.executeUpdate();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (linhasAfetadas > 0) {
                System.out.println("Usuário excluido");
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

    public static void procurarUsuario(int id_usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtendo a conexão do banco de dados
            conexao = Conexao.getConnection();

            //exclui modal pelo id
            String procurarSQL = "SELECT * FROM usuario WHERE id_usuario = ?";

            // Criar o PreparedStatement com retorno de chave gerada
            pstmt = conexao.prepareStatement(procurarSQL);
            pstmt.setInt(1, id_usuario);
            // Executar a instrução SQL
            rs = pstmt.executeQuery();

            // Se a inserção foi bem-sucedida, retorna o ID gerado
            if (rs.next()) {
                System.out.println("Usuário encontrado: " + rs.getString("nome"));
            } else{
                System.out.println("O id digitado não foi encontrado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
