package view;

import java.util.Scanner;

import controller.UsuarioController;
import exception.BusinessException;
import exception.CampoVazioException;
import exception.SenhaInvalidaException;
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
}


	
