package ProjetoIntegrador.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import ProjetoIntegrador.ProcessoDAO;
import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.functionsLambda.GenericFunctions;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ProcessoController 
{
	public void searchDBPROCESSO(TextField nomeProcesso, Stage stage, TableView<ProcessoDAO> tabela
			, TableColumn<ProcessoDAO, String> columnTipoProcesso, TableColumn<ProcessoDAO, String> columnAutor,
			TableColumn<ProcessoDAO, String> columnDescricao,
			TableColumn<ProcessoDAO, Integer> idProcesso,
			TableColumn<ProcessoDAO, ProcessoDAO> columnVerProcesso)
	{
		SqlConnection startCon = new SqlConnection();
		Object conversao = new Object();	
		Alert alerta = new Alert(AlertType.NONE);
		String processo = nomeProcesso.getText();
		conversao = startCon.initalRequires();	
		GenericFunctions functions = new GenericFunctions();
		
		try (Connection conn = (Connection) conversao)
		{
			String query = "SELECT * FROM processo WHERE "+
							"(numero_processo = ? OR numero_tribunal = ? OR instancia_processo = ? OR "+
							"tipo_processo = ? OR polo_processo = ? OR representantes_processo = ? OR "+
							"finalidade_processo = ? OR referencia = ? OR autor_processo = ? OR "+
							"reu_processo = ?)";
			
			if (nomeProcesso.getText().isEmpty())
			{
				alerta.setAlertType(AlertType.WARNING);
				alerta.setContentText("FIELD pesquisa processo IS EMPTY");
				alerta.show();		
				return;
			}
			
			try (PreparedStatement st = conn.prepareStatement(query))
			{
				st.setString(1, processo);
				st.setString(2, processo);
				st.setString(3, processo);
				st.setString(4, processo);
				st.setString(5, processo);
				st.setString(6, processo);
				st.setString(7, processo);
				st.setString(8, processo);
				st.setString(9, processo);
				st.setString(10, processo);
				
				ResultSet rs = st.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				
				if (columnsNumber <= 0)
				{
					alerta.setAlertType(AlertType.WARNING);
					alerta.setContentText("Processo (" + processo + ") nao existe ou ainda nao foi publicado..");
					alerta.show();				
					return;
				}
				else if (columnsNumber > 0)
				{ 					
						functions.showTable(conn, rs, stage, tabela,
								columnTipoProcesso, columnAutor,
								columnDescricao, idProcesso, columnVerProcesso);
				}
				else
				{
					alerta.setAlertType(AlertType.ERROR);
					alerta.setContentText("INESPERED FAILURE, TRY AGAIN SOON");
					alerta.show();
				}
			
			}
			catch(Exception e)
			{
				System.out.println("ERROR IN PREPAREDSTATEMENT ON METHOD 'searchDBProcess'...\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO CONNECT INTO DATABASE.\nERROR: " + e);
			System.exit(-1);
		}
	}
	

}