module exerciciosJavaFx {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	
	opens ProjetoIntegrador;
	opens ProjetoIntegrador.db.files;
	opens Contador;
	opens ProjetoIntegrador.fxml;
	opens ProjetoIntegrador.controller;
	opens ProjetoIntegrador.getData;
}