package org.ielena.pokedex.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;

public class PreloaderController implements Initializable {

    @FXML private ProgressBar progressBar;
    @FXML private Label loadingLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setStatusMessage(String message){
        Platform.runLater(() -> loadingLabel.setText(message));
    }
    public void setProgressBarProgress(double progress){
        Platform.runLater(() -> progressBar.setProgress(progress));
    }

}
