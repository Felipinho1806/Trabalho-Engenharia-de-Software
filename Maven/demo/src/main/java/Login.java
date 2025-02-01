import java.util.Scanner;
public class Login extends Usuario {
    
    public static void main(String[] args) {

        int estaOk = 1;
        Scanner sc = new Scanner(System.in);

        while (estaOk == 1) {
            System.out.println("Digite o email: ");
            String email = sc.nextLine();
            System.out.println("Digite a senha: ");
            String password = sc.nextLine();

            if (email.isEmpty() || password.isEmpty()) {
                System.out.println("Email e senha são obrigatórios.");
                continue;
            }

            estaOk = login(email, password);

        }
        sc.close();
    }   
}
