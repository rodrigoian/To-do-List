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
	
	public void atualizar(Usuario usuario) {
		service.atualizar(usuario);
		}
}
