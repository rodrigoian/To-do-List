package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{
	
	private Connection conn;
	
	//injeçao de dependencia pro conn ficar disponivel
	//para todos os metodos dessa classe
	public UsuarioDaoJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO cadastro.usuario "
					+ "(nome_usuario, email, senha) "
				+ "VALUES (?,?,?)");
			
			st.setString(1,obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getSenha());
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				//salva o id novo do vendedor
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					//pegar o valor do id gerado na posição 1, pois
					//vai ser a primeira coluna do .getGeneratedKeys();
					int id = rs.getInt(1);
					//atribui o id gerado dentro do objeto seller
					obj.setId(id);
				}
				DB.closeResultSet(rs);
		}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatemente(st);
		}
	}

	@Override
	public void update(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
