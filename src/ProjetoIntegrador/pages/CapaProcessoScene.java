package ProjetoIntegrador.pages;

import java.net.URL;

import ProjetoIntegrador.controller.GenerateCapaProcesso;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CapaProcessoScene 
{
	
	public void capaSceneSpawn(Stage stage, GenerateCapaProcesso capa) throws Exception
	{
		try 
		{
			URL arquivoFXML = getClass().getResource("CapaProcesso.fxml");
			GridPane raiz = FXMLLoader.load(arquivoFXML);
			Scene cenaHome = new Scene(raiz, 900, 900);
			stage.setResizable(false);
			stage.setTitle("Capa do Processo");
			stage.setScene(cenaHome);
			stage.show();
			capa.instanciaMetodos(capa.getIdProcesso());
		}
		catch(Exception e)
		{
			System.out.println("FAILURE AT 'CapaProcessoScene' in method 'capaProcessoScene'\nError: " + e);
		}
	}
}
