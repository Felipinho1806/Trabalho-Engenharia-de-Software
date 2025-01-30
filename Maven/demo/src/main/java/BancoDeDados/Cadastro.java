package BancoDeDados;

import java.util.Scanner;

public class Cadastro extends Conexao {

    public static void main(String[] args) {

        int estaOk = 1;
        Scanner sc = new Scanner(System.in);

        while (estaOk == 1) {

            System.out.println("Digite seu nome:");
            String Nome = sc.nextLine();
            System.out.println("Digite o email:");
            String Email = sc.nextLine();
            System.out.println("Digite a senha:");
            String Senha = sc.nextLine();

            if (Email.isEmpty() || Senha.isEmpty()|| Nome.isEmpty()) {
                System.out.println("Todos os campos são obrigatórios.");
                continue;
            }

            estaOk = cadastro(Email, Senha, Nome);
        }
        sc.close();
    }
}
