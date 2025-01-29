

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

}
