package ProjetoIntegrador.fxml;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageInit implements Initializable
{
	
	public void homePageScene(Stage stage) throws IOException
	{
		URL arquivoFXML = getClass().getResource("HomePage.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);
        
        Scene novaCena = new Scene(raiz);
        
        stage.setResizable(false);
        stage.setScene(novaCena);
        stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
