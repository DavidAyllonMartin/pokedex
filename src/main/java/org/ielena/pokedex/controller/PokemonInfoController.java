package org.ielena.pokedex.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.ielena.pokedex.model.Pokemon;
import org.ielena.pokedex.utils.ColorAnalyzer;


public class PokemonInfoController extends Controller {

    @FXML
    private Label id, name, type, height, weight, hp, attack, defense, specialAttack, specialDefense, speed;
    @FXML
    private ImageView image;
    @FXML
    private ProgressBar attack_pb, hp_pb, defense_pb, specialAttack_pb, specialDefense_pb, speed_pb;
    @FXML
    private AnchorPane infoPane;

    public void click1(MouseEvent mouseEvent) {
        getMediator().notify(this, 1);
    }

    public void setData(Pokemon pokemon) {

        setBackgroundColor(pokemon);

        id.setText(String.format("#%03d", pokemon.getId()));
        name.setText(pokemon.getName());

        if (pokemon.getType2() == null) {
            type.setText(pokemon.getType1());
        } else {
            type.setText(pokemon.getType1() + " / " + pokemon.getType2());
        }

        image.setImage(pokemon.getImage());

        height.setText(String.format("%.1f m", pokemon.getHeight()));
        weight.setText(String.format("%.1f kg", pokemon.getWeight()));

        hp.setText(String.valueOf(pokemon.getHp()));
        attack.setText(String.valueOf(pokemon.getAttack()));
        defense.setText(String.valueOf(pokemon.getDefense()));
        specialAttack.setText(String.valueOf(pokemon.getSpecialAttack()));
        specialDefense.setText(String.valueOf(pokemon.getSpecialDefense()));
        speed.setText(String.valueOf(pokemon.getSpeed()));

        attack_pb.setProgress(pokemon.getAttack() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(attack_pb);

        defense_pb.setProgress(pokemon.getDefense() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(defense_pb);

        specialAttack_pb.setProgress(pokemon.getSpecialAttack() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(specialAttack_pb);

        specialDefense_pb.setProgress(pokemon.getSpecialDefense() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(specialDefense_pb);

        speed_pb.setProgress(pokemon.getSpeed() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(speed_pb);

        hp_pb.setProgress(pokemon.getHp() / (double) Pokemon.MAX_STAT);
        changeProgressBarColor(hp_pb);
    }

    private void setBackgroundColor(Pokemon pokemon) {
        Color dominantColor = ColorAnalyzer.getDominantColor(pokemon.getImage());
        String backgroundColor = String.format("-fx-background-color: rgba(%d, %d, %d, 1.0);",
                (int) (dominantColor.getRed() * 255),
                (int) (dominantColor.getGreen() * 255),
                (int) (dominantColor.getBlue() * 255));
        infoPane.setStyle(backgroundColor);
    }

    private void changeProgressBarColor(ProgressBar progressBar) {
        double progress = progressBar.getProgress();
        if (progress < (36.0 / Pokemon.MAX_STAT)) {
            progressBar.getStyleClass().add("progress-bar-low");
        } else if (progress < (75.0 / Pokemon.MAX_STAT)) {
            progressBar.getStyleClass().add("progress-bar-mid");
        } else if (progress < (100.0 / Pokemon.MAX_STAT)) {
            progressBar.getStyleClass().add("progress-bar-high");
        } else {
            progressBar.getStyleClass().add("progress-bar-extraHigh");
        }
    }
}
