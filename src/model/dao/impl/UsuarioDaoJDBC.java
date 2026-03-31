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
					+ "(nome_usuario, email, senha, cpf) "
				+ "VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS); //precisa disso para cadastrar o id no sistema java
			
			st.setString(1,obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getSenha());
			st.setString(4, obj.getCpf());
		
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				
				//salva o id novo 
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					//pegar o valor do id gerado na posição 1, pois
					//vai ser a primeira coluna da tabela
					Long id = rs.getLong(1);
					//atribui o id gerado dentro do objeto
					obj.setId(id);
				}
				DB.closeResultSet(rs);
		}
		}
		catch(SQLException e) {
			//checa se email ou cpf já existem no banco pelo erro que ele dá
			
			 if (e.getMessage().contains("email")) {
			        throw new DbIntegrityException("Email já cadastrado.");
			    }

			    if (e.getMessage().contains("cpf")) {
			        throw new DbIntegrityException("CPF já cadastrado.");
			    }
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
					           + "SET email = ?, senha = ? "
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
	public Usuario findByCPF(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM usuario "
					+ "WHERE cpf = ?");
			
			st.setString(1,cpf);
			rs = st.executeQuery();
			
			//checa pra ver se veio resultado da query
			if(rs.next()) {
				Usuario usuario = new Usuario();
	            usuario.setId(rs.getLong("id")); // Ajuste o nome da coluna de ID conforme seu banco
	            usuario.setNome(rs.getString("nome"));
	            usuario.setEmail(rs.getString("email"));
	            usuario.setSenha(rs.getString("senha"));
	            usuario.setCpf(rs.getString("cpf"));
	            
	            return usuario; // Retorna o usuário preenchido
			} else {
				throw new DbException("Usuario não encontrado");
			}
		
		} catch (SQLException e) {
		throw new DbException(e.getMessage());
		}
	finally {
		DB.closeStatemente(st);
		DB.closeResultSet(rs);
	}	
	}

	@Override
	public List<Usuario> findAll() {
		
		return null;
	}


	}

