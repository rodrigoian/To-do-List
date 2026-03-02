package service;

import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {
	private UsuarioDao dao;
	
	public UsuarioService(UsuarioDao dao) {
		this.dao = dao;
	}
	
	public void cadastrar(Usuario usuario) {
		//verifica se os campos não estão vazios
		if(usuario.getNome().isEmpty() || usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()) {
			throw new RuntimeException("Erro: Campos vazios");
		}
		
		validarSenha(usuario.getSenha());
		

		dao.insert(usuario);
	}

	
	
	private void validarSenha(String senha) {
		if (senha.length() < 6) {
			throw new RuntimeException("Senha fraca, por favor que ela tenha mais de 6 digitos e pelo menos um caracter especial");
		}
		//se tiver pelo menos um caracter especial
		boolean temEspecial = senha.matches(".*[^a-zA-Z0-9].*");
		
		if(!temEspecial) {
			throw new RuntimeException("Senha deve ter pelo menos um caracter especial");
		}
		}
		
	}

