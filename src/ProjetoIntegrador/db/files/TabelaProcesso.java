package ProjetoIntegrador.db.files;

import java.sql.*;

public class TabelaProcesso 
{
	public void criaTabela(Connection conn)
	{
		try 
		{
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS processo" + 
					"(id_processo SERIAL PRIMARY KEY NOT NULL," + 
					"numero_processo VARCHAR(100) NOT NULL," + 
					"numero_tribunal VARCHAR(100) NOT NULL,"+
					"instancia_processo VARCHAR(100) NOT NULL,"+
					"tipo_processo VARCHAR(100) NOT NULL,"+
					"processo_publicado integer CHECK (processo_publicado = 1 OR processo_publicado = 0),"+
					"peca_procedual BYTEA NOT NULL," + 
					"polo_processo VARCHAR(100) NOT NULL,"+
					"representantes_processo VARCHAR(100) NOT NULL,"+
					"finalidade_processo VARCHAR(100) NOT NULL,"+
					"data_processo DATE NOT NULL,"+
					"referencia VARCHAR(100) NOT NULL,"+
					"autor_processo VARCHAR(100) NOT NULL,"+
					"reu_processo VARCHAR(100) NOT NULL,"+
					"descricao_processo VARCHAR(500) NOT NULL,"+ 
					"id_criador_processo INTEGER NOT NULL"+
					");";
			
			stmt.executeUpdate(sql);
			System.out.println("TABLE processos CREATED SUCEFULLY");
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR: IMPOSSIBLE TO CREATE OR UPDATE TABLE PROCESSOS\nERROR:" + e);
			System.exit(-1);
		}
	}
}
