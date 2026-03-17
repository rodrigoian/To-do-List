package service;

import exception.CampoVazioException;
import exception.SenhaInvalidaException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {
	private UsuarioDao dao;
	
	public UsuarioService(UsuarioDao dao) {
		this.dao = dao;
	}
	
	public void cadastrar(Usuario usuario) {
		//verifica se os campos não estão vazios
		validarCampoVazio(usuario);
		validarSenha(usuario.getSenha());
		
		dao.insert(usuario);
	}
	
	public void atualizar(Usuario usuario) {
		//validação de campos vazios
		validarCampoVazio(usuario);
		validarSenha(usuario.getSenha());
		dao.update(usuario);
	}
	public void deletar(Long id) {
		if (id == null) {
			throw new CampoVazioException("Erro: Campos Vazios!");
		}
		dao.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void validarSenha(String senha) {
		if (senha.length() < 6) {
			throw new SenhaInvalidaException("Senha deve ter pelo menos 6 digitos");
		}
		//se tiver pelo menos um caracter especial
		boolean temEspecial = senha.matches(".*[^a-zA-Z0-9].*");
		
		if(!temEspecial) {
			throw new SenhaInvalidaException("Senha deve ter pelo menos um caracter especial");
		}
		}
	
	private void validarCampoVazio(Usuario usuario) {
		if (usuario.getId() == null || 
		        usuario.getNome() == null || usuario.getNome().isBlank() ||
		        usuario.getEmail() == null || usuario.getEmail().isBlank() ||
		        usuario.getSenha() == null || usuario.getSenha().isBlank() )
			throw new CampoVazioException("Erro: Campos vazios");{
		}
	}
	
		
	}
	

