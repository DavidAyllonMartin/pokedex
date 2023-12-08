package org.ielena.pokedex.preloader;

import org.ielena.pokedex.PokedexApplication;
import org.ielena.pokedex.controller.PreloaderController;
import org.ielena.pokedex.controller.ProgressMessage;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppPreloader extends Preloader {

    private Stage preloaderStage;
    private PreloaderController preloaderController;

    @Override
    public void start(Stage stage) throws Exception {

        this.preloaderStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(PokedexApplication.class.getResource("splash-screen.fxml"));
        Scene scene           = new Scene(fxmlLoader.load());
        preloaderController   = fxmlLoader.getController();

        scene.setFill(Color.TRANSPARENT);

        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        preloaderStage.setScene(scene);
        preloaderStage.setResizable(false);
        preloaderStage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification progressNotification) {

    }

    @Override
    public void handleApplicationNotification(PreloaderNotification preloaderNotification) {
        if (preloaderNotification instanceof ProgressMessage) {
            preloaderController.setStatusMessage(((ProgressMessage) preloaderNotification).getMessage());
            preloaderController.setProgressBarProgress(((ProgressMessage) preloaderNotification).getProgress());
        }
    }

    @Override
    public boolean handleErrorNotification(ErrorNotification errorNotification) {
        return super.handleErrorNotification(errorNotification);
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}