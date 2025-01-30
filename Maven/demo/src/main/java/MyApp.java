import BancoDeDados.Login;
import BancoDeDados.Cadastro;
import java.util.Scanner;

public class MyApp {
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        System.out.println("Já possui cadastro? (S/N)");
        String resposta = sc.nextLine();

        if(resposta.toLowerCase().equals("n")) {
            Cadastro.main(args);
        }
        else {
            Login.main(args);
        }
    }
}
