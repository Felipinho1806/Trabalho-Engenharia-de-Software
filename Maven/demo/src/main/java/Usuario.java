

public class Usuario {
    
    private String nome;
    private String email;
    private String senha;
    private int id;
    
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

    public void cadastrarCliente(String senha, String email) {
        setEmail(email);
        setSenha(senha);
        System.out.println("Cliente cadastrado.");
    }

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
