package ProjetoIntegrador.getData;

import ProjetoIntegrador.controller.GenerateProcessoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GetProcessItens 
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
	
	@FXML
	private Button botaoPublicar;

	@FXML
	private Button botaoCriar;
	
	public void publicarProcesso()
	{
		Stage stage = (Stage) botaoPublicar.getScene().getWindow();
		GenerateProcessoController controller = new GenerateProcessoController();
		controller.insertPublicaProcesso(tribunal, instancia, tipoProcesso,
				poloProcesso, representanteProcesso, finalidadeProcesso, 
				referenciaProcesso, autorProcesso, reuProcesso, descricaoProcesso, stage);
	}
	
	public void criarProcesso()
	{
		Stage stage = (Stage) botaoCriar.getScene().getWindow();
		GenerateProcessoController controller = new GenerateProcessoController();
		controller.insertCriaProcesso(tribunal, instancia, tipoProcesso,
				poloProcesso, representanteProcesso, finalidadeProcesso, 
				referenciaProcesso, autorProcesso, reuProcesso, descricaoProcesso, stage);
		
	}
}
