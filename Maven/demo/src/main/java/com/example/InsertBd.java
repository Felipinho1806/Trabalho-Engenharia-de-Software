package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertBd {

    public static void main(String[] args) {

        int estaOk = 0;
        Scanner sc = new Scanner(System.in);

        while (estaOk != 1) {
                
            Connection conexao = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            System.out.println("Digite o nome:");
            String nome = sc.nextLine();
            System.out.println("Digite o email:");
            String email = sc.nextLine();
            System.out.println("Digite a senha:");
            String senha = sc.nextLine();

            try {
                // Carregar o driver do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Estabelecer a conexão com o banco de dados
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_vvv", "", "");

                String verificacaoEmail = "SELECT COUNT(*) FROM usuario WHERE email = ?";
                pstmt = conexao.prepareStatement(verificacaoEmail);
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();

                rs.next();
                if(rs.getInt(1) > 0) {
                    System.out.println("Esse email já está cadastrado!");
                    
                } else {
                    estaOk = 1;

                    // Criar a instrução SQL para inserir dados
                    String inserirSQL = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

                    // Criar o PreparedStatement
                    pstmt = conexao.prepareStatement(inserirSQL);

                    // Definir os valores dos parâmetros
                    pstmt.setString(1, nome);  // 1º parâmetro
                    pstmt.setString(2, email); // 2º parâmetro
                    pstmt.setString(3, senha); // 3º parâmetro

                    // Executar a instrução SQL
                    int linhasAfetadas = pstmt.executeUpdate();

                    // Exibir mensagem de sucesso
                    if (linhasAfetadas > 0) {
                        System.out.println("Nova linha inserida com sucesso!");
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
            }
            sc.close();
        }
}
