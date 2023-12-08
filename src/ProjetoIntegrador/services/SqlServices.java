package ProjetoIntegrador.services;

import java.sql.*;
public class SqlServices 
{
	public void insertIntoUsuarioLogado(Connection conexao, String nomeUsuario, int adm, int idUsuario)
	{
		CleanDatabase limpar = new CleanDatabase();
		try (Connection conn = conexao)
		{
			limpar.deleteUsuarioLogado(conn);
			
			String inserir = "INSERT INTO usuarioLogado (nome_usuario, usuario_adm, id_usuario) VALUES" +
							"(?, ?, ?);";
			try (PreparedStatement ps = conn.prepareStatement(inserir);)
			{
				ps.setString(1, nomeUsuario);
				ps.setInt(2, adm); 
				ps.setInt(3, idUsuario);
				try 
				{
					ps.execute();
				} 
				catch (Exception e) 
				{
					System.out.println("FAILURE TRYING TO EXECUTE QUERY.\nERROR: " + e);
				}
			} 
			catch (Exception e) 
			{
				System.out.println("FAILURE TRYING PREPARED STATMENT.\nERROR: " + e);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR TRYING GET CONNECTION.\nERROR: " + e);
		}
	}
}
