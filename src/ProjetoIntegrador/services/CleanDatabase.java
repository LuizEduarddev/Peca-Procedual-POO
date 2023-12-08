package ProjetoIntegrador.services;

import java.sql.*;

public class CleanDatabase 
{
	void deleteUsuarioLogado (Connection conn)
	{
		String query = "SELECT * FROM usuarioLogado";
		
		try (Statement st = conn.createStatement())
		{
			try (ResultSet rs = st.executeQuery(query))
			{
				query = "DELETE FROM usuarioLogado";
				
				try 
				{
					st.execute(query);
				}
				catch(Exception e)
				{
					System.out.println("FAILURE TRYING TO CLEAN DATASET");
				}
			}
			catch(Exception e)
			{
				System.out.println("ALREADY CLEAN...");
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING CREATE STATEMENT.\nERROR: " + e);
		}
	}
}
