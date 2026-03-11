package applictation;

import java.util.Scanner;

import controller.UsuarioController;
import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;
import service.UsuarioService;
import view.UsuarioView;

public class Program {
	public static void main(String [] args) {
		//instanciando tudo para dar RUN no main 
		
		Scanner sc = new Scanner(System.in);
		UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();
		UsuarioService service = new UsuarioService(usuarioDao);
		UsuarioController controller = new UsuarioController(service);
		UsuarioView view = new UsuarioView(sc,controller);
	
		view.cadastrar();
		
		
		

		sc.close();
    }

	}

