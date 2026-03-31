package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.UsuarioController;
import db.DbIntegrityException;
import exception.BusinessException;
import model.entities.Usuario;

//injeção de dependencia para que todas as funçoes aceitem scanner automatico
public class UsuarioView {
	UsuarioController controller;
	Scanner sc;
	
	
	public UsuarioView(Scanner sc, UsuarioController controller) {
		this.sc = sc;
		this.controller = controller;
	}
	
	
	public void cadastrar() {
		boolean validacao = false;
		
		
		while(!validacao) {
		System.out.println("Bem vindo ao cadastro de usuário!! \n "
				+ "Por favor, escolha quantas pessoas serão cadastradas");
		try {
			int x = sc.nextInt();
			sc.nextLine();
			
			//cadastra direto no usuario e no controller e trata a senha
			for (int i = 0; i < x; i++) {
				CadastrarValidacao();
			}
			validacao = true;
		
		}catch(InputMismatchException e) {
			System.out.println("erro na digitação");
			sc.nextLine();
		}
	}
	}
	private void CadastrarValidacao() {
		boolean validacao = false;
		
		while(!validacao){
			
		try {	
			System.out.println("Digite o nome do usuário: ");
			String nome = sc.nextLine();
	
			System.out.println("Digite o e-mail do usuário: ");
			String email = sc.nextLine();
	
			System.out.println("Digite a senha do usuário: ");
			String senha = sc.nextLine();
			
			System.out.println("Digite o cpf do usuário: ");
			String cpf = sc.nextLine();
			
			//mudar para controler, isso nao existe no view
			Usuario usuario = new Usuario(nome,email,senha,cpf);
			controller.cadastrar(usuario);
			
			System.out.println("Cadastro realizado. Id:  " + usuario.getId());
		
			validacao = true;
	
		//usar o Exception Pai para melhor escalamento
			
		}catch(BusinessException e) {
			System.out.println("❌ Erro no cadastro: Tente novamente. " + e.getMessage());
			
		}catch (DbIntegrityException e) {
		    System.out.println("Erro: " + e.getMessage());
	}
		}
  }
	
	public void atualizarNoBanco() {
		try {	
			
		System.out.println("digite o cpf do usuario que você deseja atualizar: ");
		String cpf = sc.nextLine();
		
		System.out.println("digite o email que você deseja atualizar: ");
		String email = sc.nextLine();
		
		System.out.println("digite o senha que você deseja atualizar: ");
		String senha = sc.nextLine();
		
		//para imprimir o resultado pegamos o usuario la do service e caminhamos para chegar ate aqui
		Usuario usuario = controller.atualizar(cpf,email,senha);
		System.out.println("atualizado com sucesso!" + usuario.toString());
		
		}catch(BusinessException e) {
			System.out.println("❌ Erro na atualização: Tente novamente. " + e.getMessage());
		
		}catch(RuntimeException e) {
			System.out.println("❌ Usuario não encontrado. " + e.getMessage());
		}
	}
	
	public void ApagarDoBanco() {
		try {
			System.out.println("Digite o Id que você deseja que seja deletado: ");
			Long id = sc.nextLong();
			sc.nextLine();
		
			controller.deletar(id);
			System.out.println("deletado com sucesso: ");
			
			}catch(BusinessException e) {
				System.out.println("❌ Erro na atualização: Tente novamente. " + e.getMessage());
			
			}catch(RuntimeException e) {
				System.out.println("❌ Usuario não encontrado. " + e.getMessage());
			}
		
	}
	
	public void menu() {
	try {
	    int opcao;

	    do {
	        System.out.println("\n=== MENU ===");
	        System.out.println("1 - Cadastrar usuário");
	        System.out.println("2 - Atualizar usuário");
	        System.out.println("3 - Deletar usuário");
	        System.out.println("0 - Sair");
	        System.out.print("Escolha: ");

	        opcao = sc.nextInt();
	        sc.nextLine();

	        switch (opcao) {

	            case 1:
	                cadastrar();
	                break;

	            case 2:
	                atualizarNoBanco();
	                break;
	                
	            case 3:
	                ApagarDoBanco();
	                break;  

	            case 0:
	                System.out.println("Encerrando...");
	                break;

	            default:
	                System.out.println("Opção inválida.");
	        }

	    } while (opcao != 0);
	}catch(InputMismatchException e) {
		System.out.println();
		sc.nextLine();
	}
	}
}



	
