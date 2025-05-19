/**
 * 
 */
/**
 * 
 */
module Jeu_de_15 {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	
	opens application to javafx.graphics, javafx.fxml;
    opens application.controller to javafx.graphics, javafx.fxml;
    opens application.model to javafx.graphics, javafx.fxml;
    opens application.view to javafx.graphics, javafx.fxml;
}