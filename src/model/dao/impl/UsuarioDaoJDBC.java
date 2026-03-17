package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
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
				+ "VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS); //precisa disso para cadastrar o id no sistema java
			
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
					Long id = rs.getLong(1);
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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE cadastro.usuario "
					           + "SET nome_usuario = ?, email = ?, senha = ? "
					           + "WHERE id_usuario = ?");
			
			st.setString(1,obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getSenha());
			st.setLong(4,obj.getId());
			
			int rowsAffected = st.executeUpdate();
			
			//para validar se realmente aconteceu o update
			if (rowsAffected == 0) {
				throw new DbException("Usuario não encontrado");
			}
			
			
		} catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatemente(st);
			}
		}


	@Override
	public void deleteById(Long id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"DELETE FROM CADASTRO.USUARIO "
					+ "WHERE "
					+ "id_usuario = ?");
			
			st.setLong(1,id);
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new DbException("Usuario não encontrado");
			}
		
		} catch (SQLException e) {
		throw new DbIntegrityException(e.getMessage());
	}
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
