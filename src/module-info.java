module projet_Resto_CIR3 {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	exports projet_Resto_CIR3;
	opens projet_Resto_CIR3 to javafx.graphics;
}