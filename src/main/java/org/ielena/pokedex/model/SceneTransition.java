package org.ielena.pokedex.model;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class SceneTransition {

    // Método para realizar una transición de desvanecimiento
    public static void fadeTransition(Node node, double fromValue, double toValue, int durationMillis) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(durationMillis), node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.play();
    }

}

