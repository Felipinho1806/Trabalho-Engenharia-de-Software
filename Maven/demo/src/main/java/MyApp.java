import java.util.Scanner;


public class MyApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /* TESTE EXCLUIR MODAL
        Modal t1 = new Modal();
        System.out.println("id a ser excluido:");
        String idModalStr = sc.nextLine(); 
        int id_modal = Integer.parseInt(idModalStr);
        t1.excluirModal(id_modal); */

        /* TESTE CADASTRO MODAL
        System.out.println("Capacidade:");
        String capacidadeStr = sc.nextLine(); 
        int capacidade = Integer.parseInt(capacidadeStr);
        System.out.println("Tipo");
        String tipo = sc.nextLine();
        System.out.println("Categoria");
        String categoria = sc.nextLine();
        System.out.println("marca");
        String marca = sc.nextLine();

        t1.cadastroModal(capacidade, tipo, categoria, marca);*/
     
        

        /* TESTE CADASTRO E LOGIN USUARIO
        System.out.println("Já possui cadastro? (S/N)");
        String resposta = sc.nextLine();

        if(resposta.toLowerCase().equals("n")) {
            Cadastro.main(args);
        }
        else {
            Login.main(args);
        }*/

        /* TESTE CADASTRO E LOGIN USUARIO
        Usuario u1 = new Usuario();

        System.out.println("id a ser excluido:");
        String idUsuarioStr = sc.nextLine(); 
        int id_usuario = Integer.parseInt(idUsuarioStr);

        u1.excluirUsuario(id_usuario);*/

        /* TESTE CADASTRO PONTO DE VENDAS
        PontoDeVendas p1 = new PontoDeVendas();
        System.out.println("nome");
        String nome = sc.nextLine();
        System.out.println("endereco");
        String endereco = sc.nextLine();
        System.out.println("telefone");
        String telefone = sc.nextLine();

        p1.cadastroPontoDeVenda(nome, endereco, telefone);*/

        /* TESTE EXCLUIR PONTO DE VENDAS
        PontoDeVendas p1 = new PontoDeVendas();
        System.out.println("id ponto de vendas");
        String idPDVStr = sc.nextLine(); 
        int id_pontoDeVendas = Integer.parseInt(idPDVStr);

        p1.excluirPontoDeVenda(id_pontoDeVendas); */

        /* TESTE CADASTRO PROPRIETARIO
        ProprietarioModal  pm1 = new ProprietarioModal(); 
        System.out.println("email");
        String email = sc.nextLine();
        System.out.println("telefone");
        String telefone = sc.nextLine();

        pm1.cadastroProprietarioModal(email, telefone);*/
        
        
        /* TESTE EXCLUIR PROPRIETARIO
        ProprietarioModal  pm1 = new ProprietarioModal();    
        System.out.println("id proprietario modal");
        String idPMStr = sc.nextLine(); 
        int id_proprietarioModal = Integer.parseInt(idPMStr);

        pm1.excluirProprietarioModal(id_proprietarioModal);*/

         /* TESTE CADASTRO LOCAL 
        Local  l1 = new Local(); 
        System.out.println("nome");
        String nome = sc.nextLine();
        System.out.println("endereco");
        String Endereco = sc.nextLine();

        l1.cadastrolocal(nome, Endereco);*/
        
        Local  l1 = new Local(); 
        System.out.println("id local");
        String idlocalStr = sc.nextLine(); 
        int id_local = Integer.parseInt(idlocalStr);

        l1.excluirLocal(id_local);





    }
}
