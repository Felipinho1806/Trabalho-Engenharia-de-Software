import BancoDeDados.Usuario;

public class Funcionario extends Usuario {
    
    public void realizarVenda() {
        System.out.println("Venda realizada.");
    }

    public void consultarReserva() {
        System.out.println("Id da reserva: " + getId());
    }

}
