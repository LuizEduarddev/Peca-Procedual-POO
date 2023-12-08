package ProjetoIntegrador.db.files;

import java.sql.*;

public class TabelaUsuarios 
{
	public void criaTabela(Connection conn)
	{
		try 
		{
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS usuarios" + 
					"(id_usuario SERIAL PRIMARY KEY NOT NULL," + 
					"nome_usuario VARCHAR(100) NOT NULL," + 
					"usuario_adm INTEGER CHECK (usuario_adm= 1 OR usuario_adm = 0),"+
					"senha_usuario VARCHAR(20) NOT NULL,"+
					"cpf_cnpj VARCHAR(14) NOT NULL,"+
					"oab VARCHAR(15)"+
					");";
			
			stmt.executeUpdate(sql);
			System.out.println("TABLE usuarios CREATED SUCEFULLY");
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR: IMPOSSIBLE TO CREATE OR UPDATE TABLE USUARIOS\nERROR:" + e);
			System.exit(-1);
		}
	}
}
