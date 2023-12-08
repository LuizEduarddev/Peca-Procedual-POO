package ProjetoIntegrador.pages;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomePageScene 
{
	public void homePage(Stage stage) throws Exception
	{
		String arquivoCSS = getClass().getResource("HomePage.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("HomePage.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);
		Scene cenaHome = new Scene(raiz, 1200, 700);
		cenaHome.getStylesheets().add(arquivoCSS);
		stage.setResizable(false);
		stage.setTitle("Login de usuários");
		stage.setScene(cenaHome);
		stage.show();
	}
}
