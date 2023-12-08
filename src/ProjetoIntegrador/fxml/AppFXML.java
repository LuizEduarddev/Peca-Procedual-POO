package ProjetoIntegrador.fxml;

import java.net.URL;
import java.sql.Connection;

import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppFXML extends Application{
	
	@FXML
	private TextField usuarioField;
	@FXML
	private PasswordField senhaField;
	@FXML
	private Button botaoEntrar;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		String arquivoCSS = getClass().getResource("Login.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("Login.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);
		Scene cenaLogin = new Scene(raiz, 400, 400);
		cenaLogin.getStylesheets().add(arquivoCSS);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Login de usuários");
		primaryStage.setScene(cenaLogin);
		primaryStage.show();
	}
	
	public void getLogin()
	{
		Stage stage = (Stage) botaoEntrar.getScene().getWindow();
		LoginController controlador = new LoginController();
		controlador.searchDBLOGIN(usuarioField, senhaField, stage);
		
	}
	
	void tentaConexao()
	{
		SqlConnection query = new SqlConnection();
		Object conversao = query.initalRequires();	
		try (Connection conn = (Connection) conversao)
		{
			try 
			{				
				query.iniciaTabelas(conn);
			}
			catch(Exception e)
			{
				System.out.println("FAILURE TRYING TO CREATE TABLES.\nERROR: " + e);
				System.exit(-1);
			}	
		}
		catch(Exception e)
		{
			System.out.println("FAILURE TRYING TO TEST THE CONNECTION.\nERROR: " + e);
			System.exit(-1);
		}
	}
	
	public static void main(String[] args) 
	{	
		AppFXML app = new AppFXML();
		app.tentaConexao();

		launch(args);
	}
}
