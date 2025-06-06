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
	requires junit;
	requires org.junit.jupiter.api;
	
	opens application to javafx.graphics, javafx.fxml;
    opens application.controller to javafx.graphics, javafx.fxml;
    opens application.model to javafx.graphics, javafx.fxml;
    opens application.view to javafx.graphics, javafx.fxml;
    opens ressources to javafx.graphics, javafx.fxml;
}