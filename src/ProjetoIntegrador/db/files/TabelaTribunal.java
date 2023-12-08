package ProjetoIntegrador.db.files;

import java.sql.Connection;
import java.sql.Statement;

public class TabelaTribunal 
{
	public void criaTabela(Connection conn)
	{
		try 
		{
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS numeroTribunal" + 
					"(nome_tribunal VARCHAR(100) NOT NULL," + 
					"numero_Tribunal VARCHAR(100) NOT NULL" + 
					");";
			
			stmt.executeUpdate(sql);
			System.out.println("TABLE numeroTribunal CREATED SUCEFULLY");
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR: IMPOSSIBLE TO CREATE OR UPDATE TABLE PROCESSOS\nERROR:" + e);
			System.exit(-1);
		}
	}
}
