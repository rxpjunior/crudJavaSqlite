package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection conectionSqlite() {
		String jdbc = "jdbc:sqlite:";
		String pasta = "src/db/";
		String db = "compensadb.db";
		String caminho = jdbc + pasta + db;
		try {
			// create a database connection
			Connection connection = DriverManager.getConnection(caminho);
			System.out.println("Conexao ao banco de dados estabelecida com sucesso");
			return connection;

		} catch (Exception e) {
			System.out.println("Erro ao estabelecer conexao com o banco de dados");
			return null;
		}
	}

}
