package ProjetoIntegrador.functionsLambda;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import ProjetoIntegrador.ProcessoDAO;
import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.controller.GenerateCapaProcesso;
import ProjetoIntegrador.pages.CapaProcessoScene;
import ProjetoIntegrador.pages.HomePageScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GenericFunctions 
{	
	public void showTable(Connection conn,
			ResultSet rs,
			Stage stage, 
			TableView<ProcessoDAO> tabela,
			TableColumn<ProcessoDAO, String> columnTipoProcesso, 
			TableColumn<ProcessoDAO, String> columnAutor,
			TableColumn<ProcessoDAO, String> columnDescricao,
			TableColumn<ProcessoDAO, Integer> idProcesso,
			TableColumn<ProcessoDAO, ProcessoDAO> columnVerProcesso) throws SQLException
	{
		tabela.setVisible(true);
		columnTipoProcesso.setCellValueFactory(
				new PropertyValueFactory<>("tipoProcesso"));
		columnAutor.setCellValueFactory(
				new PropertyValueFactory<>("autorProcesso"));
		columnDescricao.setCellValueFactory(
				new PropertyValueFactory<>("descricaoProcesso"));
		idProcesso.setCellValueFactory(
				new PropertyValueFactory<>("idProcesso"));
		
		tabela.setItems(getList(rs, conn));
	
		//addButtonToTable(columnVerProcesso, stage);
	}
	
	public int getUsuarioLogado(Connection conn)
	{
		String query = "SELECT * FROM usuarioLogado";
		
		try(Statement st = conn.createStatement())
		{
			try (ResultSet rs = st.executeQuery(query))
			{
				if (rs.next())
				{
					int idUsuario = rs.getInt("id_usuario");
					return idUsuario;
				}
			}
			catch(Exception e)
			{
				System.out.println("Failure trying to get resultset in class 'ProcessoController' "
						+ "in method 'getUsuarioLogado'");
			}
		}
		catch(Exception e)
		{
			System.out.println("failure trying to get Statment at class 'ProcessoControler'"
					+ " and in method 'getUsuarioLogado'");
		}
		
		return 000000;
	}
	
	public ObservableList<ProcessoDAO> getList(ResultSet rs, Connection conn) throws SQLException
	{
		Alert alerta = new Alert(AlertType.NONE);
		ObservableList<ProcessoDAO> processList = FXCollections.observableArrayList();
		ProcessoDAO process;
		while (rs.next())
		{
			int idCriador = rs.getInt("id_criador_processo");
			int publicado = rs.getInt("processo_publicado");
			int idUsuarioLogado = getUsuarioLogado(conn); 
			if ((publicado == 0) && (idCriador != idUsuarioLogado))
			{
				alerta.setAlertType(AlertType.INFORMATION);
				alerta.setContentText("UM DOS PROCESSOS AINDA NAO FOI PUBLICADO\n"
						+ "E VOCE NAO É O CRIADO DELE!");
				alerta.show();
			}
			else
			{				
				process = new ProcessoDAO(
						rs.getString("autor_processo"), 
						rs.getString("tipo_processo"), 
						rs.getString("descricao_processo"),
						rs.getInt("id_processo"));
				processList.add(process);
			}
		}
		
		return FXCollections.observableArrayList(processList);
	}
	
	public void addButtonToTable(TableColumn<ProcessoDAO, ProcessoDAO> columnVerProcesso, Stage stage) 
	{   
        Callback<TableColumn<ProcessoDAO, ProcessoDAO>, TableCell<ProcessoDAO, ProcessoDAO>> cellFactory = new Callback<TableColumn<ProcessoDAO, ProcessoDAO>, TableCell<ProcessoDAO, ProcessoDAO>>() {
            @Override
            public TableCell<ProcessoDAO, ProcessoDAO> call(final TableColumn<ProcessoDAO, ProcessoDAO> param) {
              final TableCell<ProcessoDAO, ProcessoDAO> cell = new TableCell<ProcessoDAO, ProcessoDAO>() {
                private final Button btn = new Button("Ver Processo");

                @Override
                public void updateItem(ProcessoDAO item, boolean empty) {
                  super.updateItem(item, empty);
                  if (empty) {
                    setGraphic(null);
                  } else {
                    setGraphic(btn);
                  }
                }

              {
                btn.setOnAction(event -> 
                {
                	ProcessoDAO data = getTableView().getItems().get(getIndex());
                	getRowById(data.getIdProcesso(), stage);
                });
              }
            };
            return cell;
            }
          };
          columnVerProcesso.setCellFactory(cellFactory);
    }
	
	public void getRowById(int idProcesso, Stage stage)
	{
		SqlConnection conversao = new SqlConnection();
		
		try (Connection conn = (Connection) conversao.initalRequires())
		{
			String query = "SELECT * FROM processo WHERE (id_processo = ?)";
			try (PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setInt(1, idProcesso);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next())
				{
					try 
					{
						
						CapaProcessoScene capaScene = new CapaProcessoScene();
						GenerateCapaProcesso capaOne = new GenerateCapaProcesso();
						capaOne.setIdProcesso(idProcesso);
						capaScene.capaSceneSpawn(stage, capaOne);
					}
					catch(Exception e)
					{
						System.out.println("FAILURE AT TRYING TO DO 'capaProcessoScene' at 'getRowById' on class 'GenericFunctions'\n"
								+ "ERROR: " + e);
					}
				}
				else
				{
					System.out.println("NO ROWS RETURNED SEARCHING FOR 'id_processo' AT 'getRowById'");
					return;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("WAS A FAILURE TRYING TO GET PREPAREDSTATMENT IN CLASS 'GenericFunctions' AND IN METHOD 'getRowById'.\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("WAS A FAILURE TRYING TO GET CONNECTION IN CLASS 'GenericFunctions' AND IN METHOD 'getRowById'.\nERROR: " + e);
		}
	}
	
	public String generateNumberProcess(Connection conn)
	{
		String addNumeroProcesso = "";
		String query = "SELECT numero_processo FROM processo";
		
		try (Statement st = conn.createStatement())
		{
			
			addNumeroProcesso = verificaNumeroRepetido(query, st);
			
			return addNumeroProcesso;
			
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO GET STATMENT.\nPLEASE TRY AGAIN SOON..\nERROR: " + e);
		}
		
		return "FAILURE";
	}
	
	
	private String verificaNumeroRepetido(String query, Statement st)
	{
		String resultado = "";
		String conversao = "";
		int getNumber = 0;
		int processNumber = 0;
		
		try (ResultSet rs = st.executeQuery(query))
		{
			for (int i = 0; i < 7; i++) 
	        {
	            getNumber = gerarNumeroAleatorio();
	            resultado += Integer.toString(getNumber);
	        }
	        
			getNumber = Integer.parseInt(resultado);
			
			while (rs.next())
			{
				conversao = rs.getString("numero_processo");
				processNumber = Integer.parseInt(conversao);
				if (processNumber == getNumber)
		        {
		        	verificaNumeroRepetido(query, st);
		        }
		        else
		        {
		        	return resultado;
		        }
				
			}
	        return resultado;
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO GET RESULTSET IN METHOD\n"
					+ "'verificaNumeroRepetido'");
		}
		return "FAILURE";
	}
	
	private static int gerarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(9); 
    }
	
	public String generateNumberTribunal(String tribunal)
	{
		SqlConnection sql = new SqlConnection();
		Object conversao = sql.initalRequires();
		try (Connection conn = (Connection) conversao)
		{
			String query = "SELECT * FROM numeroTribunal";
			
			try (Statement st = conn.createStatement())
			{
				ResultSet rs = st.executeQuery(query);
				while (rs.next())
				{
					String nomeTribunal = rs.getString("nome_tribunal");
					boolean areEqual = nomeTribunal.equals(tribunal);
					if (areEqual)
					{
						return rs.getString("numero_tribunal");
					}
					
				}
				return injectTribunal(tribunal, conn, rs);
			}
			catch(Exception e)
			{
				System.out.println("FAILURE TRYING TO GET STATMENT..\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO CONNECT INTO DATABASE..\nERROR: " + e);
		}
		System.out.println("WASNT POSSIBLE TO RETURN numero_tribunal.\nTRY AGAIN.");
		System.exit(-1);
		return "";
	}
	
	private String injectTribunal(String nomeTribunal, Connection conn, ResultSet rs)
	{
		String resultado = "";
		int getNumber = 0;
		
		for (int i = 0; i < 7; i++) 
        {
            getNumber = gerarNumeroAleatorio();
            resultado += Integer.toString(getNumber);
        }
        
		String query = "INSERT INTO numeroTribunal (nome_tribunal, numero_tribunal) values "
				+ "(?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(query))
		{
			ps.setString(1, nomeTribunal);
			ps.setString(2, resultado);
			
			try
			{
				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0)
				{
					System.out.println("Alterado com sucesso....");
					return resultado;					
				}
				else
				{
					System.out.println("FAILURE TRYING TO INJECT IN TABLE numeroTribunal.");
				}
			}
			catch(Exception e)
			{
				System.out.println("FAILURE TRYING TO INJECT IN TABLE numeroTribunal.\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO GET PREPAREDSTATMENT..\nERROR: " + e);
		}
		return resultado;
	}
	
	public void insertIntoProcessData(Stage stage, Alert alerta, PreparedStatement ps, HomePageScene homeScene) throws Exception
	{
		try
		{			
			int rowsAffect = ps.executeUpdate();
			if (rowsAffect < 0)
			{
				alerta.setAlertType(AlertType.ERROR);
				alerta.setContentText("ANY ROWS AFFECTED, PLEASE TRY AGAIN.");
				alerta.show();
				homeScene.homePage(stage);
			}
			else
			{				
				alerta.setAlertType(AlertType.CONFIRMATION);
				alerta.setContentText("Processo criado com sucesso!\nVoltando para a página inicial");
				alerta.show();
				homeScene.homePage(stage);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILED TRYING TO INSERT IN TABLE processos IN METHOD"
					+ " 'insertIntoProcessData'");
			System.exit(-1);
		}
	}
}
