package application.controller;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import application.JeuDuQuinzeMain;

public class CommentJouerController {
	
	/**
	 * Scène précédente pour permettre de revenir en arrière.
	 * Doit être définie avant de charger cette page.
	 */
	
	private Scene previousScene;

    // À appeler quand tu charges cette page
    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    @FXML
    private StackPane mainPane;

    @FXML
    private AnchorPane page1, page2, page3, page4, page5, page6, 
                       page7, page8, page9, page10, page11, 
                       page12, page13, page14, page15;

    private List<AnchorPane> pages;
    private int currentPage = 0;

    @FXML
    public void initialize() {
        pages = Arrays.asList(page1, page2, page3, page4, page5, page6,
        		              page7, page8, page9, page10, page11, page12, 
        		              page13, page14, page15);
        
        showPage(currentPage);
    }

    private void showPage(int index) {
        for (int i = 0; i < pages.size(); i++) {
            pages.get(i).setVisible(i == index);
        }
    }

    @FXML
    private void handleNext() {
        if (currentPage < pages.size() - 1) {
            currentPage++;
            showPage(currentPage);
        }
    }

    @FXML
    private void handlePrev() {
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    }
    

	@FXML
	private void clicBoutonRetourArriere(ActionEvent event) {
	    JeuDuQuinzeMain.activerPrincipale();
	}

}