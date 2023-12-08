package ProjetoIntegrador.controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import ProjetoIntegrador.SqlConnection;
import ProjetoIntegrador.pages.HomePageScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GenerateCapaProcesso implements Initializable
{
	@FXML
	private Label tribunalId;
	
	@FXML
	private Label instanciaId;
	
	@FXML
	private Label tipoProcessoId;
	
	@FXML
	private Label poloProcessoId;
	
	@FXML
	private Label representanteProcessoId;
	
	@FXML
	private Label finalidadeProcessoId;
	
	@FXML
	private Label referenciaProcessoId;
	
	@FXML
	private Label autorProcessoId;
	
	@FXML
	private Label reuProcessoId;
	
	@FXML
	private Label descricaoProcessoId;
	
	@FXML
	private Label dataProcessoId;
	
	@FXML
	private Button button;
	
	private int idProcesso;
	
	
	public int getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(int idProcesso) {
		this.idProcesso = idProcesso;
	}

	public void instanciaMetodos(int idProcesso)
	{
		SqlConnection conversao = new SqlConnection();
		try (Connection conn = (Connection) conversao.initalRequires())
		{
			String query = "SELECT * FROM processo WHERE (id_processo = ?)";
			try (PreparedStatement ps = conn.prepareStatement(query))
			{
				ps.setInt(1, idProcesso);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next())
				{
					
					String tribunal = rs.getString("numero_tribunal");
					String instancia = rs.getString("instancia_processo");
					String tipoProcesso = rs.getString("tipo_processo");
					String poloProcesso = rs.getString("polo_processo");
					String representanteProcesso = rs.getString("representantes_processo");
					String finalidadeProcesso = rs.getString("finalidade_processo");
					String referenciaProcesso = rs.getString("referencia");
					String autorProcesso = rs.getString("autor_processo");
					String reuProcesso = rs.getString("reu_processo");
					String descricaoProcesso = rs.getString("descricao_processo");
					Date dataProcesso = rs.getDate("data_processo");
					String dataTransformada = returnData(dataProcesso);
					
					try
					{
						tribunalId.setText(tribunal);
						instanciaId.setText(instancia);
						tipoProcessoId.setText(tipoProcesso);
						poloProcessoId.setText(poloProcesso);
						representanteProcessoId.setText(representanteProcesso);
						finalidadeProcessoId.setText(finalidadeProcesso);
						referenciaProcessoId.setText(referenciaProcesso);
						autorProcessoId.setText(autorProcesso);
						reuProcessoId.setText(reuProcesso);
						descricaoProcessoId.setText(descricaoProcesso);
						dataProcessoId.setText(dataTransformada);
					}
					catch(Exception e)
					{
						System.out.println("Error at 'GenerateCapaProcesso' in method 'instanciaMetodos'\nError: " + e);
					}
				}
				else
				{
					System.out.println("No rows returned, try again...");
					return;
				}
			}
			catch(Exception e)
			{
				System.out.println("Error at 'GenerateCapaProcesso' in method 'instanciaMetodos'\nError: " + e);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error at 'GenerateCapaProcesso' in method 'instanciaMetodos'\nError: " + e);
		}
	}
	
	private String returnData(Date dateProcesso)
	{
		String DATE_FORMAT = "MMM d, yyyy HH:mm a";
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		String formattedDate = formatter.format(dateProcesso);
		return formattedDate;
	}
	
	public void goToHomePage() throws Exception
	{
		Stage stage = (Stage) button.getScene().getWindow();
		HomePageScene homeScene = new HomePageScene();
		homeScene.homePage(stage);
	}

	public Label getTribunalId() {
		return tribunalId;
	}

	public void setTribunalId(Label tribunalId) {
		this.tribunalId = tribunalId;
	}

	public Label getInstanciaId() {
		return instanciaId;
	}

	public void setInstanciaId(Label instanciaId) {
		this.instanciaId = instanciaId;
	}

	public Label getTipoProcessoId() {
		return tipoProcessoId;
	}

	public void setTipoProcessoId(Label tipoProcessoId) {
		this.tipoProcessoId = tipoProcessoId;
	}

	public Label getPoloProcessoId() {
		return poloProcessoId;
	}

	public void setPoloProcessoId(Label poloProcessoId) {
		this.poloProcessoId = poloProcessoId;
	}

	public Label getRepresentanteProcessoId() {
		return representanteProcessoId;
	}

	public void setRepresentanteProcessoId(Label representanteProcessoId) {
		this.representanteProcessoId = representanteProcessoId;
	}

	public Label getFinalidadeProcessoId() {
		return finalidadeProcessoId;
	}

	public void setFinalidadeProcessoId(Label finalidadeProcessoId) {
		this.finalidadeProcessoId = finalidadeProcessoId;
	}

	public Label getReferenciaProcessoId() {
		return referenciaProcessoId;
	}

	public void setReferenciaProcessoId(Label referenciaProcessoId) {
		this.referenciaProcessoId = referenciaProcessoId;
	}

	public Label getAutorProcessoId() {
		return autorProcessoId;
	}

	public void setAutorProcessoId(Label autorProcessoId) {
		this.autorProcessoId = autorProcessoId;
	}

	public Label getReuProcessoId() {
		return reuProcessoId;
	}

	public void setReuProcessoId(Label reuProcessoId) {
		this.reuProcessoId = reuProcessoId;
	}

	public Label getDescricaoProcessoId() {
		return descricaoProcessoId;
	}

	public void setDescricaoProcessoId(Label descricaoProcessoId) {
		this.descricaoProcessoId = descricaoProcessoId;
	}

	public Label getDataProcessoId() {
		return dataProcessoId;
	}

	public void setDataProcessoId(Label dataProcessoId) {
		this.dataProcessoId = dataProcessoId;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
	    this.instanciaMetodos(getIdProcesso());	
	}
}
