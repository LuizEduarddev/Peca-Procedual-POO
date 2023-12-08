package ProjetoIntegrador;

import java.sql.*;
import ProjetoIntegrador.db.files.TabelaProcesso;
import ProjetoIntegrador.db.files.TabelaTribunal;
import ProjetoIntegrador.db.files.TabelaUsuarioLogado;
import ProjetoIntegrador.db.files.TabelaUsuarios;

public class SqlConnection {
	final String url = "jdbc:postgresql://localhost:5432/projetoIntegrador";
	final String usuario = "postgres";
	final String senha = "1234";
	
	public Object initalRequires()
	{
		try 
		{
			Connection conn = DriverManager
					.getConnection(url, usuario, senha);
			return conn;
		} 
		catch (Exception e) 
		{
			System.out.println("Nao foi possivel conectar-se ao banco de dados.\n"
					+ "ERROR: " + e);
			return -1;
		}	
	}
	
	public void iniciaTabelas(Connection conn)
	{	
		TabelaTribunal table1 = new TabelaTribunal();
		TabelaProcesso table2 = new TabelaProcesso();	
		TabelaUsuarios table3 = new TabelaUsuarios();	
		TabelaUsuarioLogado table4 = new TabelaUsuarioLogado();
	
		table1.criaTabela(conn);
		table2.criaTabela(conn);
		table3.criaTabela(conn);
		table4.criaTabela(conn);
	}
}
