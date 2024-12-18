package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Conexao {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Connection conexao = null;

        try {
            // Carregar o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_vvv", "", "");
            
            // Executar a consulta
            ResultSet rsCliente = conexao.createStatement().executeQuery("SELECT * FROM usuario");

            // Ler email e senha do usuário
            System.out.println("Digite o email: ");
            String email = sc.nextLine();
            System.out.println("Digite a senha: ");
            String senha = sc.nextLine(); 

            boolean usuarioAutenticado = false;

            // Iterar pelos resultados da consulta
            while (rsCliente.next()) {
                // Verificar se o email e a senha correspondem
                if (email.equals(rsCliente.getString("email")) && senha.equals(rsCliente.getString("senha"))) {
                    usuarioAutenticado = true;
                    System.out.println("Seja bem-vindo " + rsCliente.getString("nome"));
                    break; // Sai do loop se o login for bem-sucedido
                }
            }

            // Verificar se o login foi bem-sucedido
            if (!usuarioAutenticado) {
                System.out.println("Erro para se logar!!");
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não localizado.");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        } finally {
            // Fechar a conexão se não for mais necessária
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }
}
