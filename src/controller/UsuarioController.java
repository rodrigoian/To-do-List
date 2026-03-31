package controller;

import model.entities.Usuario;
import service.UsuarioService;

public class UsuarioController {
	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	public void cadastrar(Usuario usuario) {
	service.cadastrar(usuario);
	}
	
	public Usuario atualizar(String cpf ,String email, String senha) {
		return service.atualizar(cpf,email,senha);
		
		
		}
	
	public void deletar(Long id) {
		service.deletar(id);
		}
}
