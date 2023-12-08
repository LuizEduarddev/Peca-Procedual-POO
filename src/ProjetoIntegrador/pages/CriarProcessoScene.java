package ProjetoIntegrador.pages;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CriarProcessoScene 
{
	public void criaScene(Stage stage) throws Exception
	{
		URL arquivoFXML = getClass().getResource("CriaProcesso.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);
		
		Scene cenaHome = new Scene(raiz, 700, 700);
		
		stage.setResizable(false);
		stage.setTitle("Login de usuários");
		stage.setScene(cenaHome);
		stage.show();
	}
}
