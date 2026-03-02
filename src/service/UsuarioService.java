package service;

import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {
	private UsuarioDao dao;
	
	public UsuarioService(UsuarioDao dao) {
		this.dao = dao;
	}
	
	public void cadastrar(Usuario usuario) {
		dao.insert(usuario);
	}
}
