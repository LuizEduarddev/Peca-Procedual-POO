package ProjetoIntegrador.getData;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GetProcessoCapa 
{
	@FXML
	private TextField tribunal;
	
	@FXML
	private TextField instancia;
	
	@FXML
	private TextField tipoProcesso;
	
	@FXML
	private TextField poloProcesso;
	
	@FXML
	private TextField representanteProcesso;
	
	@FXML
	private TextField finalidadeProcesso;
	
	@FXML
	private TextField referenciaProcesso;
	
	@FXML
	private TextField autorProcesso;
	
	@FXML
	private TextField reuProcesso;
	
	@FXML
	private TextField descricaoProcesso;
	
	public void capaProcessoScene(Stage stage) throws Exception
	{
		URL arquivoFXML = getClass().getResource("HomePage.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);
		Scene cenaHome = new Scene(raiz, 900, 900);
		
		
		stage.setResizable(false);
		stage.setTitle("Login de usuários");
		stage.setScene(cenaHome);
		stage.show();
	}
}
