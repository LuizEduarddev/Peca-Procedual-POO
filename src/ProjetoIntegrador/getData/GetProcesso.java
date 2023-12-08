package ProjetoIntegrador.getData;

import ProjetoIntegrador.ProcessoDAO;
import ProjetoIntegrador.controller.ProcessoController;
import ProjetoIntegrador.pages.CriarProcessoScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GetProcesso 
{
	@FXML
	private TextField processoField;
	
	@FXML
	private Button botaoCriar;
	
	@FXML
	private Button botaoPesquisar;
	
	@FXML
	private TableView<ProcessoDAO> tabela;
	
	@FXML
	private TableColumn<ProcessoDAO, String> columnTipoProcesso;
	
	@FXML
	private TableColumn<ProcessoDAO, String> columnAutor;
	
	@FXML
	private TableColumn<ProcessoDAO, String> columnDescricao;
	
	@FXML
	private TableColumn<ProcessoDAO, Integer> idProcesso;
	
	@FXML
	private TableColumn<ProcessoDAO, ProcessoDAO> columnVerProcesso;
	
	public void acessarProcesso()
	{
		Stage stage = (Stage) botaoPesquisar.getScene().getWindow();
		ProcessoController controller = new ProcessoController();
		controller.searchDBPROCESSO(processoField, stage, tabela, columnTipoProcesso
				,columnAutor, columnDescricao, idProcesso, columnVerProcesso);
	}
	
	public void criarProcesso() throws Exception
	{
		Stage stage = (Stage) botaoCriar.getScene().getWindow();
		CriarProcessoScene cenaProcesso = new CriarProcessoScene();
		cenaProcesso.criaScene(stage);
	}
	
}
