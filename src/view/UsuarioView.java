package view;

import java.util.Scanner;

import controller.UsuarioController;
import exception.BusinessException;
import model.entities.Usuario;

//injeção de dependencia para que todas as funçoes aceitem scanner automatico
public class UsuarioView {
	UsuarioController controller;
	Scanner sc;
	
	
	public UsuarioView(Scanner sc,UsuarioController controller) {
		this.sc = sc;
		this.controller = controller;
	}
	
	
	public void cadastrar() {
		System.out.println("Bem vindo ao cadastro de usuário!! \n "
				+ "Por favor, escolha quantas pessoas serão cadastradas");
		
		int x = sc.nextInt();
		sc.nextLine();
		
		//cadastra direto no usuario e no controller e trata a senha
		for (int i = 0; i < x; i++) {
			CadastrarValidacao();
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
	
			Usuario usuario = new Usuario(nome,email,senha);
			controller.cadastrar(usuario);
			System.out.println("cadastro realizado! " + usuario.getId());
			
			validacao = true;
			
		
		
		//usar o Exception Pai para melhor escalamento
		}catch(BusinessException e) {
			System.out.println("❌ Erro no cadastro: Tente novamente. " + e.getMessage());
		
		}
	}
  }
	
	public void atualizarNoBanco() {
		try {
		System.out.println("Digite o Id que você deseja ser atualizado: ");
		Long id = sc.nextLong();
		sc.nextLine();
		
		System.out.println("digite o nome que você deseja atualizar: ");
		String nome = sc.nextLine();
		
		System.out.println("digite o email que você deseja atualizar: ");
		String email = sc.nextLine();
		
		System.out.println("digite o senha que você deseja atualizar: ");
		String senha = sc.nextLine();
		
		
		Usuario usuario = new Usuario(id,nome,email,senha);
		//mudar o id fora do construtor
		controller.atualizar(usuario);
		System.out.println("atualizado com sucesso: " + usuario.toString());
		
		}catch(BusinessException e) {
			System.out.println("❌ Erro na atualização: Tente novamente. " + e.getMessage());
		
		}catch(RuntimeException e) {
			System.out.println("❌ Usuario não encontrado");
		}
	}
	public void menu() {

	    int opcao;

	    do {
	        System.out.println("\n=== MENU ===");
	        System.out.println("1 - Cadastrar usuário");
	        System.out.println("2 - Atualizar usuário");
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

	            case 0:
	                System.out.println("Encerrando...");
	                break;

	            default:
	                System.out.println("Opção inválida.");
	        }

	    } while (opcao != 0);
	}
}


	
