package br.com.sistema.escolar.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection connection = null;

		try (InputStream input = Conexao.class.getClassLoader().getResourceAsStream("application.properties")) {//pegar valores declarados na application.properties

			Properties properties = new Properties();
			properties.load(input);
			
			String driver = properties.getProperty("spring.datasource.url");
            String user = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");            
			
			try {
				connection = DriverManager.getConnection(driver, user, password);
			} catch (SQLException e) {
				System.out.println("FALHA ao tentar criar conexão ");
				throw new RuntimeException(e);
			}

		} catch (IOException e) {
			System.out.println("FALHA ao tentar carregar aquivos de propriedades");
			e.printStackTrace();
		}
		return connection;
	}
}
