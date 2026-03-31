package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDao {
	
	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Long id);
	Usuario findByCPF(String cpf);
	List<Usuario> findAll();

}
