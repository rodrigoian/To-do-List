package view;

import java.util.Scanner;

import model.entities.Usuario;

//injeção de dependencia para que todas as funçoes aceitem scanner automatico
public class UsuarioView {
	Scanner sc;
	
	public UsuarioView(Scanner sc) {
		this.sc = sc;
	}
	
	
	//vai retornar o numero de pessoas a serem cadastradas no usuario para ser usado no controller depois
	public int mostrarMenu() {
		System.out.println("Bem vindo ao cadastro de usuário!! \n "
				+ "Por favor, escolha quantas pessoas serão cadastradas");
		
		int x = sc.nextInt();
		return x; 
		
	}
	//vai retornar o cadastro do usuario para ser usado no controller, ele ainda foi salvo no construtor
	public Usuario cadastrar() {
		System.out.println("Digite o nome do usuário: ");
		String nome = sc.nextLine();
		
		System.out.println("Digite o e-mail do usuário: ");
		String email = sc.nextLine();
		
		System.out.println("Digite a senha do usuário: ");
		String senha = sc.nextLine();
		
		return new Usuario(nome,email,senha);
		}
	
		
	

}
