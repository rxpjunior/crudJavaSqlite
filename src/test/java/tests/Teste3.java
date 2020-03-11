package tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionFactory;

public class Teste3 {

	public static void main(String[] args) throws SQLException {
		new ConnectionFactory();
		Connection c = ConnectionFactory.conectionSqlite();
		Statement stmt = null;
		stmt = c.createStatement();
		String scriptDb = "PRAGMA foreign_keys = off;\r\n" + "BEGIN TRANSACTION;\r\n" + "\r\n"
				+ "-- Table: compensacao\r\n" + "DROP TABLE IF EXISTS compensacao;\r\n"
				+ "CREATE TABLE compensacao (compensacaoId INTEGER PRIMARY KEY AUTOINCREMENT, data DATE NOT NULL, tempo DOUBLE NOT NULL, dia_compensarId INTEGER REFERENCES dia_compensar (dia_compensarId));\r\n"
				+ "\r\n" + "-- Table: dia_compensar\r\n" + "DROP TABLE IF EXISTS dia_compensar;\r\n"
				+ "CREATE TABLE dia_compensar (dia_compensarId INTEGER PRIMARY KEY AUTOINCREMENT, data DATE NOT NULL UNIQUE, descricao VARCHAR NOT NULL, compensado BOOLEAN NOT NULL DEFAULT (false));\r\n"
				+ "\r\n" + "COMMIT TRANSACTION;\r\n" + "PRAGMA foreign_keys = on;";

		System.out.println("CRIANDO O BANCO DE DADOS");
		try {
			stmt.executeUpdate(scriptDb);
		} catch (Exception e) {
			System.out.println("Erro ao tentar criar o BD. Erro: " + e);
		}

		System.out.println("INSERINDO DADOS NO BANCO DE DADOS");
		try {
			stmt.executeUpdate("Insert into dia_compensar (data, descricao) values ('24/02/2020','Ponte Carnaval');");
			stmt.executeUpdate("Insert into dia_compensar (data, descricao) values ('20/04/2020','Ponte Tiradentes');");
			stmt.executeUpdate(
					"Insert into dia_compensar (data, descricao) values ('12/06/2020','Ponte Corpus Christi');");
		} catch (Exception e) {
			System.out.println("Não foi possível inserir os dados no banco. Erro: " + e);
		}

		System.out.println("RECUPERANDO AS INFORMAÇÕES DO BANCO DE DADOS");
		try {
			ResultSet rs = stmt.executeQuery("Select * from dia_compensar;");
			System.out.println("\nImprimindo dias a compensar:\n");
			while (rs.next()) {
				System.out.println("DATA:" + rs.getString("data"));
				System.out.println("Descrição:" + rs.getString("descricao"));
				System.out.println("\n");
			}

		} catch (Exception e) {
			System.out.println("Não foi possível recuperar as informações. Erro: " + e);
		}

		System.out.println("ALTERANDO AS INFORMAÇÕES DO BANCO DE DADOS");
		try {
			stmt.executeUpdate("update dia_compensar set descricao = 'PONTE ALTERADA' where data = '24/02/2020';");
		} catch (Exception e) {
			System.out.println("Não foi possível alterar os dados no banco. Erro: " + e);
		}
		
		System.out.println("RECUPERANDO AS INFORMAÇÕES DO BANCO DE DADOS");
		try {
			ResultSet rs = stmt.executeQuery("Select * from dia_compensar;");
			System.out.println("\nImprimindo dias a compensar:\n");
			while (rs.next()) {
				System.out.println("DATA:" + rs.getString("data"));
				System.out.println("Descrição:" + rs.getString("descricao"));
				System.out.println("\n");
			}

		} catch (Exception e) {
			System.out.println("Não foi possível recuperar as informações. Erro: " + e);
		}

	}

}
