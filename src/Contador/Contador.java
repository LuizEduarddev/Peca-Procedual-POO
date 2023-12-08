package Contador;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Contador extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AtomicInteger valor = new AtomicInteger(0);
	
		Label labelTitulo = new Label("Contador");
		Label labelNumero = new Label("0");
		Label telaTroca = new Label("Execucao Funcionou");
		
		Button buttonDecrementar = new Button("Decrementar");
		Button buttonIncremente = new Button("Incrementar");
		Button trocarTela = new Button("Switch stage");
		
		buttonIncremente.setOnAction(e ->
		{
			int res = valor.incrementAndGet();
			String alterLabel = Integer.toString(res);
			labelNumero.setText(alterLabel);
		}
		);
		
		buttonDecrementar.setOnAction(e ->
		{
			int res = valor.decrementAndGet();
			if (res < 0)
			{
				labelNumero.setText("Números menores que 0 nao aparecerao...");
			}
			else {				
				String alterLabel = Integer.toString(res);
				labelNumero.setText(alterLabel);
			}
		}
		);
		
		VBox mainTela = new VBox();
		mainTela.setAlignment(Pos.CENTER);
		mainTela.setSpacing(10);
		
		HBox telaSecundaria = new HBox();
		telaSecundaria.setAlignment(Pos.CENTER);
		telaSecundaria.setSpacing(10);
		
		HBox terceiraTela = new HBox();
		
		
		terceiraTela.getChildren().add(telaTroca);
		telaSecundaria.getChildren().add(buttonDecrementar);
		telaSecundaria.getChildren().add(buttonIncremente);
		mainTela.getChildren().add(trocarTela);
		mainTela.getChildren().add(labelTitulo);
		mainTela.getChildren().add(labelNumero);
		mainTela.getChildren().add(telaSecundaria);
		
		Scene mainFrame = new Scene(mainTela, 400, 400);
		Scene switchTela = new Scene(terceiraTela, 400, 400);
		
		trocarTela.setOnAction(e ->
		{
			primaryStage.setScene(switchTela);
		});
		
		primaryStage.setScene(mainFrame);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
