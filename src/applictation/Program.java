package applictation;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class Program {
	public static void main(String [] args) {
		
		UsuarioDao UsuarioDao = DaoFactory.createUsuarioDao();
		
		Usuario u = new Usuario();
		u.setNome("rodrigo");
		u.setEmail("rodrigo@gmail.com");
		u.setSenha("12332112");
		
		UsuarioDao.insert(u);
		
    }

	}

