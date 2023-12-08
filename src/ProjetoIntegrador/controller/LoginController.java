package ProjetoIntegrador.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.pages.HomePageScene;
import ProjetoIntegrador.services.SqlServices;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController 
{
	public void searchDBLOGIN(TextField nomeUsuario, PasswordField senhaUsuario, Stage stage)
	{		
		SqlConnection startCon = new SqlConnection();
		Object conversao = new Object();	
		Alert alerta = new Alert(AlertType.NONE);
		String user = nomeUsuario.getText();
		String password = senhaUsuario.getText();
		HomePageScene homeScene = new HomePageScene();
		SqlServices inserir = new SqlServices();
		
		conversao = startCon.initalRequires();
		try 
		{
			Connection conn = (Connection) conversao;
			System.out.println("CONNECTION: OK.");
			String consultaSql = "SELECT * FROM usuarios "
					+ "WHERE (nome_usuario = ? AND senha_usuario = ?)";
			
			try
			{
				if (nomeUsuario.getText().isEmpty())
				{
					alerta.setAlertType(AlertType.WARNING);
					alerta.setContentText("FIELD nome_usuario IS EMPTY.");
					alerta.show();
					return;
				}
				
				if (senhaUsuario.getText().isEmpty())
				{
					alerta.setAlertType(AlertType.WARNING);
					alerta.setContentText("FIELD senha_usuario IS EMPTY");
					alerta.show();
					return;
				}
				
				PreparedStatement st = conn.prepareStatement(consultaSql);
				st.setString(1, user);
				st.setString(2, password);
				ResultSet rs = st.executeQuery();
				if (rs.next())
				{	
					int isAdmin = rs.getInt("usuario_adm");
					int idUsuario = rs.getInt("id_usuario");
					inserir.insertIntoUsuarioLogado(conn, user, isAdmin, idUsuario);
					homeScene.homePage(stage);
				}
				else
				{
					alerta.setAlertType(AlertType.ERROR);
					alerta.setContentText("USER OR PASSWORD INCORRECT.");
					alerta.show();
				}
			}
			catch(Exception e)
			{
				System.out.println("ERROR: " + e);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("BAD CONNECTION WITH DATA BASE....");
			System.exit(-1);
		}
		
	}
}
