package ProjetoIntegrador.db.files;

import java.sql.*;

public class TabelaUsuarioLogado 
{
	
	public void criaTabela(Connection conn)
	{
		try 
		{
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS usuarioLogado" + 
					"(nome_usuario VARCHAR(100) NOT NULL," + 
					"usuario_adm INTEGER NOT NULL,"+
					"id_usuario INTEGER NOT NULL"
					+ ");";
			
			stmt.executeUpdate(sql);
			System.out.println("TABLE usuarioLogado CREATED SUCEFULLY");
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR: IMPOSSIBLE TO CREATE OR UPDATE TABLE USUARIOLOGADO\nERROR:" + e);
			System.exit(-1);
		}
	}
	

}
