package ProjetoIntegrador;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProcessoDAO 
{
	public SimpleStringProperty  autorProcesso;
	public SimpleStringProperty  tipoProcesso;
	public SimpleStringProperty  descricaoProcesso;
	public SimpleIntegerProperty idProcesso;
	
	public ProcessoDAO(String  autorProcesso, 
					String tipoProcesso, 
					String descricaoProcesso,
					int idProcesso)
	{
		this.autorProcesso = new SimpleStringProperty(autorProcesso);
		this.tipoProcesso = new SimpleStringProperty(tipoProcesso);
		this.descricaoProcesso = new SimpleStringProperty(descricaoProcesso);
		this.idProcesso = new SimpleIntegerProperty(idProcesso);
	}
	
	public void setIdProcesso(int idProcesso)
	{
		this.idProcesso.set(idProcesso);
	}
	
	public int getIdProcesso()
	{
		return idProcesso.get();
	}
	
	public SimpleIntegerProperty getIdProcessoProperty()
	{
		return idProcesso;
	}
	
	public void setAutorProcesso(String autorProcesso)
	{
		this.autorProcesso.set(autorProcesso);
	}
	
	public String getAutorProcesso()
	{
		return autorProcesso.get();
	}
	public SimpleStringProperty  getAutorProcessoProperty()
	{
		return autorProcesso;
	}
	
	public void setTipoProcesso(String tipoProcesso) 
	{
		this.tipoProcesso.set(tipoProcesso);
	}
	
	public String getTipoProcesso()
	{
		return tipoProcesso.get();	
	}
	
	public SimpleStringProperty  getTipoProcessoProperty()
	{
		return tipoProcesso;
	}
	
	public void setDescricaoProcesso(String descricaoProcesso)
	{
		this.descricaoProcesso.set(descricaoProcesso);
	}
	
	public String getDescricaoProcesso() 
	{
		return descricaoProcesso.get();
	}
	
	public SimpleStringProperty  getDescricaoProcessoProperty()
	{
		return descricaoProcesso;
	}
}
