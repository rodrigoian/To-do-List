package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

	public class DB {
		
		private static Connection conn = null;
		
		//conectar o banco de dados
		public static Connection getConnection() {
			//se nao tiver conexao, entra no try
			if (conn == null) {
				try {
					Properties props = loadProperties();
					String url = props.getProperty("dburl");
					conn = DriverManager.getConnection(url, props);
				}
				catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
			return conn;
		}
		//fechar conexao do banco
		public static void closeConnection() {
			if (conn != null) {
				try {
					conn.close();
				} 
				catch(SQLException e) {
				throw new DbException(e.getMessage());
				}
			}
		}
		//carregar informaçoes do banco de dados
		private static Properties loadProperties() {
			try (FileInputStream fs = new FileInputStream("db.properties")) {
				Properties props = new Properties();
				props.load(fs);
				return props;
			}
			catch(IOException e) {
				throw new DbException(e.getMessage());
			}
		}
		public static void closeStatemente(Statement st) {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new db.DbException(e.getMessage());
				
				}
			}
		}
		public static void closeResultSet(ResultSet rs) {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new db.DbException(e.getMessage());
				
				}
			}
		}

	}


