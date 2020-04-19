package com.avatarduel.guicontroller.Popup;

import com.avatarduel.guicontroller.util.FXMLHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

import java.io.IOException;

/**
 * the father of popup, loads the popup fxml and has an abstract
 * method, getpopup which will be implemented by its children
 * @author G10-K03-CardGameOOP
 */
public abstract class PopupLoader {
    protected FXMLHandler fxmlHandler;
    protected Parent popupGui;
    protected Button confirmButton;
    protected Button cancelButton;
    protected Label title;

    /**
     * load the fxml, if fail, will send an alert
     * if success, inject the fxml button and label into this card
     * protected components
     */
    public PopupLoader() {
        try {
            this.fxmlHandler = new FXMLHandler("GUI/Popup/ActionForm.fxml");
            this.popupGui = fxmlHandler.getParent();
            this.title = (Label) popupGui.lookup("#title");
            this.confirmButton = (Button) popupGui.lookup("#confirm_button");
            this.cancelButton = (Button) popupGui.lookup("#cancel_button");
        }
        catch (IOException IOE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "PopupLoader.java : cannot load fxml file");
            alert.show();
        }
    }

    /**
     * create the popup, initialize all buttons and title to match
     * the popup theme
     * @return the popup
     */
    public abstract Popup getPopup();
}
