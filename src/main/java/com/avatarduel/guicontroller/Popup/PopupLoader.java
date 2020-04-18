package com.avatarduel.guicontroller.Popup;

import com.avatarduel.guicontroller.util.FXMLHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;

import java.io.IOException;

public abstract class PopupLoader <T> {
    protected FXMLHandler fxmlHandler;
    protected Parent popupGui;
    protected Button confirmButton;
    protected Button cancelButton;
    protected Label title;

    public PopupLoader() {
        try {
            this.fxmlHandler = new FXMLHandler("GUI/Popup/ActionForm.fxml");
            this.popupGui = fxmlHandler.getParent();
            this.title = (Label) popupGui.lookup("#title");
            this.confirmButton = (Button) popupGui.lookup("#confirm_button");
            this.cancelButton = (Button) popupGui.lookup("#cancel_button");
        }
        catch (IOException IOE) {
            System.out.println("ActionForm.fxml path is wrong, check file popuploader.java ");
        }
    }

    public abstract Popup getPopup();
}
