package ProjetoIntegrador.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Date;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.functionsLambda.GenericFunctions;
import ProjetoIntegrador.pages.HomePageScene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class GenerateProcessoController 
{
	public void insertCriaProcesso(TextField tribunalP, 
			TextField instanciaP, TextField tipoProcessoP, 
			TextField poloProcessoP, TextField representanteProcessoP, TextField finalidadeProcessoP,
			TextField referenciaProcessoP, TextField autorProcessoP, TextField reuProcessoP,
			TextField descricaoProcessoP, Stage stage)
	{
		SqlConnection startCon = new SqlConnection();
		Object conversao = new Object();	
		Alert alerta = new Alert(AlertType.NONE);
		GenericFunctions functions = new GenericFunctions();
		String tribunal = tribunalP.getText();
		String instancia = instanciaP.getText();
		String tipoProcesso = tipoProcessoP.getText();
		String poloProcesso = poloProcessoP.getText();
		String representanteProcesso = representanteProcessoP.getText();
		String finalidadeProcesso = finalidadeProcessoP.getText();
		String referenciaProcesso = referenciaProcessoP.getText();
		String autorProcesso = autorProcessoP.getText();
		String reuProcesso = reuProcessoP.getText();
		String descricaoProcesso = descricaoProcessoP.getText();
		Date data = new Date();
		java.sql.Date dataAtual = new java.sql.Date(data.getTime());
		HomePageScene homeScene = new HomePageScene();
		conversao = startCon.initalRequires();	
		
		if (tribunal.isEmpty() || instancia.isEmpty() || tipoProcesso.isEmpty() ||
				poloProcesso.isEmpty() || representanteProcesso.isEmpty() || finalidadeProcesso.isEmpty() ||
				referenciaProcesso.isEmpty() || autorProcesso.isEmpty() || reuProcesso.isEmpty() || 
				descricaoProcesso.isEmpty())
		{
			alerta.setAlertType(AlertType.WARNING);
			alerta.setContentText("SOME FIELD IS EMPTY.");
			alerta.show();
			return;
		}
		
		try (Connection conn = (Connection) conversao)
		{
			String query = "INSERT INTO processo (numero_processo, numero_tribunal, instancia_processo,"
					+ "tipo_processo, processo_publicado, polo_processo, "
					+ "representantes_processo, finalidade_processo, data_processo, referencia,"
					+ "autor_processo, reu_processo, descricao_processo, id_criador_processo, peca_procedual)"
					+ "values"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try (PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setString(1, functions.generateNumberProcess(conn));
				ps.setString(2, functions.generateNumberTribunal(tribunal));
				ps.setString(3, instancia);
				ps.setString(4, tipoProcesso);
				ps.setInt(5, 0); // Processo publicado | 0 = nao | 1 = sim
				ps.setString(6, poloProcesso);
				ps.setString(7, representanteProcesso);
				ps.setString(8, finalidadeProcesso);
				ps.setDate(9, dataAtual);
				ps.setString(10, referenciaProcesso);
				ps.setString(11, autorProcesso);
				ps.setString(12, reuProcesso);
				ps.setString(13, descricaoProcesso);
				ps.setInt(14, getIdCriador(conn));
				ps.setBytes(15, getBinaryData(stage, alerta));
				functions.insertIntoProcessData(stage, alerta, ps, homeScene);
			}
			catch(Exception e)
			{
				System.out.println("FAILURE TRYING TO CREATE PREPAREDSTATMENT.\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO CONNECT INTO DATABASE.\nERROR: " + e);
			System.exit(-1);
		}
	}
	
	private byte[] getBinaryData(Stage stage, Alert alerta)
	{
		alerta.setAlertType(AlertType.INFORMATION);
		alerta.setContentText("PLEASE CHOICE AN FILE FOR peca procedual");
		alerta.show();
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha um arquivo");
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            byte[] binaryData = readBinaryFile(selectedFile);
            processBinaryData(binaryData);
            return binaryData;
        }
        
        String failure = "FAILURE";
        byte[] returnByteFailure = failure.getBytes();
        
        return returnByteFailure;
	}
	
	private void processBinaryData(byte[] binaryData) {
        if (binaryData != null) {
            int maxLength = Math.min(binaryData.length, 10);
            byte[] firstBytes = Arrays.copyOfRange(binaryData, 0, maxLength);

            System.out.println("Binary Data: " + Arrays.toString(firstBytes));
        } else {
            System.out.println("Failed to read binary file.");
        }
    }
	
	private byte[] readBinaryFile(File file) {
        try 
        {
            Path filePath = file.toPath();
            return Files.readAllBytes(filePath);
        } 
        catch (Exception e) 
        {
            System.out.println("FAILURE TRYING TO READ THE BINARY DATA IN METHOD"
            		+ "'readBinaryFile'");
            return null;
        }
    }
	
	private int getIdCriador(Connection conn)
	{
		try (Statement st = conn.createStatement())
		{
			String query = "SELECT id_usuario FROM usuarioLogado";
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				int idUsuario = rs.getInt("id_usuario");
				return idUsuario;
			}
			else
			{
				System.out.println("FAILURE TRYING TO GET id_usuario.\nPLEASE TRY AGAIN.");
				System.exit(-1);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO GET STATMENT..\nERROR: " + e);
		}
		return 0;
	}
	
	public void insertPublicaProcesso(TextField tribunalP, 
			TextField instanciaP, TextField tipoProcessoP, 
			TextField poloProcessoP, TextField representanteProcessoP, TextField finalidadeProcessoP,
			TextField referenciaProcessoP, TextField autorProcessoP, TextField reuProcessoP,
			TextField descricaoProcessoP, Stage stage)
	{
		SqlConnection startCon = new SqlConnection();
		Object conversao = new Object();	
		Alert alerta = new Alert(AlertType.NONE);
		GenericFunctions functions = new GenericFunctions();
		String tribunal = tribunalP.getText();
		String instancia = instanciaP.getText();
		String tipoProcesso = tipoProcessoP.getText();
		String poloProcesso = poloProcessoP.getText();
		String representanteProcesso = representanteProcessoP.getText();
		String finalidadeProcesso = finalidadeProcessoP.getText();
		String referenciaProcesso = referenciaProcessoP.getText();
		String autorProcesso = autorProcessoP.getText();
		String reuProcesso = reuProcessoP.getText();
		String descricaoProcesso = descricaoProcessoP.getText();
		Date data = new Date();
		java.sql.Date dataAtual = new java.sql.Date(data.getTime());
		HomePageScene homeScene = new HomePageScene();
		conversao = startCon.initalRequires();	
		
		if (tribunal.isEmpty() || instancia.isEmpty() || tipoProcesso.isEmpty() ||
				poloProcesso.isEmpty() || representanteProcesso.isEmpty() || finalidadeProcesso.isEmpty() ||
				referenciaProcesso.isEmpty() || autorProcesso.isEmpty() || reuProcesso.isEmpty() || 
				descricaoProcesso.isEmpty())
		{
			alerta.setAlertType(AlertType.WARNING);
			alerta.setContentText("SOME FIELD IS EMPTY.");
			alerta.show();
			return;
		}
		
		try (Connection conn = (Connection) conversao)
		{
			String query = "INSERT INTO processo (numero_processo, numero_tribunal, instancia_processo,"
					+ "tipo_processo, processo_publicado, polo_processo, "
					+ "representantes_processo, finalidade_processo, data_processo, referencia,"
					+ "autor_processo, reu_processo, descricao_processo, id_criador_processo, peca_procedual)"
					+ "values"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try (PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setString(1, functions.generateNumberProcess(conn));
				ps.setString(2, functions.generateNumberTribunal(tribunal));
				ps.setString(3, instancia);
				ps.setString(4, tipoProcesso);
				ps.setInt(5, 1); // Processo publicado | 0 = nao | 1 = sim
				ps.setString(6, poloProcesso);
				ps.setString(7, representanteProcesso);
				ps.setString(8, finalidadeProcesso);
				ps.setDate(9, dataAtual);
				ps.setString(10, referenciaProcesso);
				ps.setString(11, autorProcesso);
				ps.setString(12, reuProcesso);
				ps.setString(13, descricaoProcesso);
				ps.setInt(14, getIdCriador(conn));
				ps.setBytes(15, getBinaryData(stage, alerta));
				functions.insertIntoProcessData(stage, alerta, ps, homeScene);
			}
			catch(Exception e)
			{
				System.out.println("FAILURE TRYING TO CREATE PREPAREDSTATMENT.\nERROR: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO CONNECT INTO DATABASE.\nERROR: " + e);
			System.exit(-1);
		}
	}
	
	
}
